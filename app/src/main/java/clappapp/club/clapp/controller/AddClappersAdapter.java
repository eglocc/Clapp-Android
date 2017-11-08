package clappapp.club.clapp.controller;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;

import clappapp.club.clapp.R;
import clappapp.club.clapp.databinding.AddContactsHeaderViewBinding;
import clappapp.club.clapp.databinding.ContactsEmptyViewBinding;
import clappapp.club.clapp.databinding.MemberListItemBinding;
import clappapp.club.clapp.model.SoftUser;
import clappapp.club.clapp.model.User;


public class AddClappersAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = AddClappersAdapter.class.getSimpleName();

    private static final int HEADER_VIEW = 21;
    private static final int EMPTY_VIEW = 22;
    private static final int ITEM_VIEW = 23;

    interface OnClickListener {
        void onClickAddButton();

        void onClickRemoveButton(int position);
    }

    private final OnClickListener mOnClickHandler;
    private SearchView mSearchView;
    private String mEmptyViewText;
    private String mPromptText;
    private ArrayList<SoftUser> mClappers;

    public AddClappersAdapter(OnClickListener onClickHandler, ArrayList<SoftUser> clappers, String promptText, String emptyViewText) {
        mOnClickHandler = onClickHandler;
        mClappers = clappers;
        mEmptyViewText = emptyViewText;
        mPromptText = promptText;
    }

    public final ArrayList<SoftUser> getClappers() {
        return mClappers;
    }

    public void addContact() {
        if (mSearchView != null) {
            SoftUser user = new SoftUser(mSearchView.getQuery().toString());
            mClappers.add(user);
            notifyDataSetChanged();
        }
    }

    public void removeContact(int position) {
        mClappers.remove(position - 1);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if (viewType == HEADER_VIEW) {
            view = inflater.inflate(R.layout.add_contacts_header_view, parent, false);
            return new EventContactsAdapterHeaderViewHolder(view);
        } else if (viewType == EMPTY_VIEW) {
            view = inflater.inflate(R.layout.contacts_empty_view, parent, false);
            return new EventContactsAdapterEmptyViewHolder(view);
        } else {
            view = inflater.inflate(R.layout.member_list_item, parent, false);
            return new EventContactsAdapterItemViewHolder(view);
        }
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof EventContactsAdapterHeaderViewHolder) {
            ((EventContactsAdapterHeaderViewHolder) holder).mPromptTextView.setText(mPromptText);
        } else if (holder instanceof EventContactsAdapterEmptyViewHolder) {
            ((EventContactsAdapterEmptyViewHolder) holder).mEmptyViewAlert.setText(mEmptyViewText);
        } else if (holder instanceof EventContactsAdapterItemViewHolder) {
            User user = getItem(position);
            ((EventContactsAdapterItemViewHolder) holder).mClapperPicture.setImageResource(R.mipmap.ic_launcher_round);
            ((EventContactsAdapterItemViewHolder) holder).mClapperName.setText(user.getName() /*+ " " + user.getSurname()*/);
            ((EventContactsAdapterItemViewHolder) holder).mClapperTitle.setText("Some Title");
            ((EventContactsAdapterItemViewHolder) holder).mEditButton.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        int dataSize = mClappers.size();
        if (dataSize > 0) {
            return dataSize + 1;
        } else {
            return 2;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0)
            return HEADER_VIEW;
        if (position == 1)
            return mClappers.size() == 0 ? EMPTY_VIEW : ITEM_VIEW;

        return ITEM_VIEW;
    }

    private User getItem(int position) {
        return mClappers.get(position - 1);
    }

    public class EventContactsAdapterHeaderViewHolder extends RecyclerView.ViewHolder {

        private AddContactsHeaderViewBinding mBinding;
        private TextView mPromptTextView;
        private SearchView mSearchView;

        public EventContactsAdapterHeaderViewHolder(View itemView) {
            super(itemView);
            mBinding = DataBindingUtil.bind(itemView);
            mPromptTextView = mBinding.promptText;
            mSearchView = mBinding.searchView;
            AddClappersAdapter.this.mSearchView = mSearchView;
        }
    }

    public class EventContactsAdapterEmptyViewHolder extends RecyclerView.ViewHolder {

        private ContactsEmptyViewBinding mBinding;
        private TextView mEmptyViewAlert;

        public EventContactsAdapterEmptyViewHolder(View itemView) {
            super(itemView);
            mBinding = DataBindingUtil.bind(itemView);
            mEmptyViewAlert = mBinding.noContactsText;
        }
    }

    //ItemViewHolder
    public class EventContactsAdapterItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private MemberListItemBinding mBinding;
        private TextView mClapperName;
        private TextView mClapperTitle;
        private ImageView mClapperPicture;
        private ImageButton mEditButton;
        private ImageButton mRemoveButton;


        public EventContactsAdapterItemViewHolder(View itemView) {
            super(itemView);
            mBinding = DataBindingUtil.bind(itemView);
            mClapperName = mBinding.memberName;
            mClapperTitle = mBinding.memberTitle;
            mClapperPicture = mBinding.memberPicture;
            mEditButton = mBinding.editButton;
            mRemoveButton = mBinding.removeButton;

            mRemoveButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mOnClickHandler.onClickRemoveButton(getAdapterPosition());
        }
    }
    //End of ItemViewHolder
}
