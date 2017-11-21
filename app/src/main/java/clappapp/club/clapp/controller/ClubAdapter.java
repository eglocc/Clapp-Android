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
import clappapp.club.clapp.databinding.ClubListItemBinding;
import clappapp.club.clapp.model.Club;
import clappapp.club.clapp.model.SoftClub;

public class ClubAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = ClubAdapter.class.getSimpleName();

    interface OnClickListener {
        void clubFollowed(ImageView v, int position);

        void clubClicked(int position);
    }

    private final ClubAdapter.OnClickListener mOnClickListener;
    private ArrayList<SoftClub> mClubs;

    public ClubAdapter(ClubAdapter.OnClickListener onClickListener, ArrayList<SoftClub> clubs) {
        mOnClickListener = onClickListener;
        mClubs = clubs;
    }

    public final ArrayList<SoftClub> getClubs() {
        return mClubs;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.club_list_item, parent, false);
        return new ClubAdapterItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ClubAdapterItemViewHolder) {
            Club club = getItem(position);
            ((ClubAdapterItemViewHolder) holder).mClubName.setText(club.getName());
            ((ClubAdapterItemViewHolder) holder).mClubLogo.setImageResource(club.getLogoID());
        }
    }

    @Override
    public int getItemCount() {
        return mClubs.size();
    }

    private Club getItem(int position) {
        return mClubs.get(position);
    }

    public class ClubAdapterItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ClubListItemBinding mBinding;
        private ImageView mClubLogo;
        private TextView mClubName;
        private ImageView mFollowButton;

        public ClubAdapterItemViewHolder(View itemView) {
            super(itemView);
            mBinding = DataBindingUtil.bind(itemView);
            mClubLogo = mBinding.clubLogo;
            mClubName = mBinding.clubName;
            mFollowButton = mBinding.followButton;
            itemView.setOnClickListener(this);
            mFollowButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.follow_button:
                    mOnClickListener.clubFollowed((ImageView) v, getAdapterPosition());
                default:
                    mOnClickListener.clubClicked(getAdapterPosition());
            }
        }
    }
}
