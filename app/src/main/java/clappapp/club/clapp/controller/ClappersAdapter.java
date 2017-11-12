package clappapp.club.clapp.controller;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import clappapp.club.clapp.R;
import clappapp.club.clapp.databinding.ContactsEmptyViewBinding;
import clappapp.club.clapp.databinding.MemberListItemBinding;
import clappapp.club.clapp.model.SoftUser;
import clappapp.club.clapp.model.User;

public class ClappersAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = ClappersAdapter.class.getSimpleName();

    private static final int EMPTY_VIEW = 22;
    private static final int ITEM_VIEW = 23;

    interface OnClickListener {
        void clapperClicked(View v, int position);
    }

    private final ClappersAdapter.OnClickListener mOnClickListener;
    private String mEmptyViewText;
    private ArrayList<SoftUser> mClappers;

    public ClappersAdapter(ClappersAdapter.OnClickListener onClickListener, ArrayList<SoftUser> clappers, String emptyViewText) {
        mOnClickListener = onClickListener;
        mClappers = clappers;
        mEmptyViewText = emptyViewText;
    }

    public final ArrayList<SoftUser> getClappers() {
        return mClappers;
    }

    public void addContact() {

    }

    public void removeContact(int position) {
        mClappers.remove(position);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if (viewType == EMPTY_VIEW) {
            view = inflater.inflate(R.layout.contacts_empty_view, parent, false);
            return new ClappersAdapterEmptyViewHolder(view);
        } else {
            view = inflater.inflate(R.layout.member_list_item, parent, false);
            return new ClappersAdapterItemViewHolder(view);
        }
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ClappersAdapterEmptyViewHolder) {
            ((ClappersAdapterEmptyViewHolder) holder).mEmptyViewAlert.setText(mEmptyViewText);
        } else if (holder instanceof ClappersAdapterItemViewHolder) {
            User user = getItem(position);
            ((ClappersAdapterItemViewHolder) holder).mClapperPicture.setImageResource(R.mipmap.ic_launcher_round);
            ((ClappersAdapterItemViewHolder) holder).mClapperName.setText(user.getName() /*+ " " + user.getSurname()*/);
            ((ClappersAdapterItemViewHolder) holder).mClapperTitle.setText("Some Title");
        }
    }

    @Override
    public int getItemCount() {
        int dataSize = mClappers.size();
        if (dataSize > 0) {
            return dataSize;
        } else {
            return 1;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0)
            return mClappers.size() == 0 ? EMPTY_VIEW : ITEM_VIEW;

        return super.getItemViewType(position);
    }

    private User getItem(int position) {
        return mClappers.get(position);
    }

    public class ClappersAdapterEmptyViewHolder extends RecyclerView.ViewHolder {

        private ContactsEmptyViewBinding mBinding;
        private TextView mEmptyViewAlert;

        public ClappersAdapterEmptyViewHolder(View itemView) {
            super(itemView);
            mBinding = DataBindingUtil.bind(itemView);
            mEmptyViewAlert = mBinding.noContactsText;
        }
    }

    //ItemViewHolder
    public class ClappersAdapterItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private MemberListItemBinding mBinding;
        private TextView mClapperName;
        private TextView mClapperTitle;
        private ImageView mClapperPicture;
        private ImageView mClapperSelectedIcon;


        public ClappersAdapterItemViewHolder(View itemView) {
            super(itemView);
            mBinding = DataBindingUtil.bind(itemView);
            mClapperName = mBinding.memberName;
            mClapperTitle = mBinding.memberTitle;
            mClapperPicture = mBinding.memberPicture;
            mClapperSelectedIcon = mBinding.selectedSymbol;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mOnClickListener.clapperClicked(mClapperSelectedIcon, getAdapterPosition());
        }
    }
    //End of ItemViewHolder
}
