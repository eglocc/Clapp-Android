package clappapp.club.clapp.controller;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import clappapp.club.clapp.MyApplication;
import clappapp.club.clapp.R;
import clappapp.club.clapp.databinding.ContactsEmptyViewBinding;
import clappapp.club.clapp.databinding.MemberListItemBinding;
import clappapp.club.clapp.model.User;


public class EventContactsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = EventContactsAdapter.class.getSimpleName();

    private static final int EMPTY_VIEW = 22;

    interface OnClickListener {
        void onClickRemoveButton(View view, int position);
    }

    private final OnClickListener mOnClickHandler;
    private ArrayList<User> mContacts;

    public EventContactsAdapter(OnClickListener onClickhandler) {
        mOnClickHandler = onClickhandler;
        mContacts = new ArrayList<>();
    }

    public void addContact(User user) {
        mContacts.add(user);
        notifyDataSetChanged();
    }

    public void removeContact(int position) {
        mContacts.remove(position);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == EMPTY_VIEW) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contacts_empty_view, parent, false);
            return new EventContactsAdapterEmptyViewHolder(view);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.member_list_item, parent, false);
            return new EventContactsAdapterItemViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof EventContactsAdapterItemViewHolder) {
            User user = getItem(position);
            ((EventContactsAdapterItemViewHolder) holder).mBinding.memberPicture.setImageResource(R.mipmap.ic_launcher_round);
            ((EventContactsAdapterItemViewHolder) holder).mBinding.memberName.setText(user.getName() /*+ " " + user.getSurname()*/);
            ((EventContactsAdapterItemViewHolder) holder).mBinding.memberTitle.setText("Some Title");
            ((EventContactsAdapterItemViewHolder) holder).mBinding.editButton.setVisibility(View.GONE);
        } else if (holder instanceof EventContactsAdapterEmptyViewHolder) {
            ((EventContactsAdapterEmptyViewHolder) holder).mBinding.noContactsText.
                    setText(MyApplication.getResourcesStaticly().getString(R.string.no_contacts));
        }
    }

    @Override
    public int getItemCount() {
        int dataSize = mContacts.size();
        Log.d(TAG, "Data size: " + dataSize);
        if (dataSize > 0) {
            return dataSize;
        } else {
            return 1;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (mContacts.size() == 0) {
            return EMPTY_VIEW;
        }
        return super.getItemViewType(position);
    }

    private User getItem(int position) {
        return mContacts.get(position);
    }

    public class EventContactsAdapterItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private MemberListItemBinding mBinding;

        public EventContactsAdapterItemViewHolder(View itemView) {
            super(itemView);
            mBinding = DataBindingUtil.bind(itemView);
            mBinding.removeButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mOnClickHandler.onClickRemoveButton(v, getAdapterPosition());
        }
    }

    public class EventContactsAdapterEmptyViewHolder extends RecyclerView.ViewHolder {

        private ContactsEmptyViewBinding mBinding;

        public EventContactsAdapterEmptyViewHolder(View itemView) {
            super(itemView);
            mBinding = DataBindingUtil.bind(itemView);
        }
    }
}
