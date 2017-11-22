package clappapp.club.clapp.controller;

import android.databinding.DataBindingUtil;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import clappapp.club.clapp.R;
import clappapp.club.clapp.databinding.EventListItemBinding;
import clappapp.club.clapp.model.DataHelper;
import clappapp.club.clapp.model.Enums;
import clappapp.club.clapp.model.SoftEvent;

public class EventAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = EventAdapter.class.getSimpleName();

    interface OnClickListener {
        void eventClicked(View v, int position, long id);

        void eventFollowed(View v, int position, long id);
    }

    private DataHelper mDataHelper;
    private final EventAdapter.OnClickListener mOnClickListener;
    private ArrayList<SoftEvent> mEvents;
    private boolean mShowHeader;

    public EventAdapter(EventAdapter.OnClickListener onClickListener, ArrayList<SoftEvent> events, boolean showHeader) {
        mDataHelper = DataHelper.getInstance();
        mOnClickListener = onClickListener;
        mEvents = events;
        mShowHeader = showHeader;
    }

    public final ArrayList<SoftEvent> getEvents() {
        return mEvents;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_list_item, parent, false);
        return new EventCardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof EventCardViewHolder) {
            SoftEvent event = getItem(position);
            HashMap<Enums.Privacy, Integer> logoMap = mDataHelper.getPrivacyLogoMap();

            if (mShowHeader) {
                ((EventCardViewHolder) holder).mClubName.setText(event.getClubName());
                ((EventCardViewHolder) holder).mClubIcon.setImageResource(event.getClubIcon());
                Enums.Privacy privacy = event.getPrivacy();
                ((EventCardViewHolder) holder).mPrivacyLabel.setText(privacy.toString());
                ((EventCardViewHolder) holder).mPrivacyLabel
                        .setCompoundDrawablesWithIntrinsicBounds(logoMap.get(privacy), 0, 0, 0);
            } else {
                ((EventCardViewHolder) holder).mHeaderCard.setVisibility(View.GONE);
            }
            ((EventCardViewHolder) holder).mEventTitle.setText(event.getTitle());
            ((EventCardViewHolder) holder).mEventDescription.setText(event.getDescription());
            ((EventCardViewHolder) holder).mEventImage.setImageResource(event.getImageLink());
            ((EventCardViewHolder) holder).mEventDate.setText(event.getDateString());
            ((EventCardViewHolder) holder).mEventTime.setText(event.getTimeString());
            ((EventCardViewHolder) holder).mEventPlace.setText(event.getPlace());
        }
    }

    @Override
    public int getItemCount() {
        return mEvents == null ? 0 : mEvents.size();
    }

    private SoftEvent getItem(int position) {
        return mEvents.get(position);
    }

    public class EventCardViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private EventListItemBinding mBinding;

        //Preview UI
        private CardView mHeaderCard;
        private ImageView mClubIcon;
        private TextView mClubName;
        private TextView mPrivacyLabel;
        private TextView mEventTitle;
        private TextView mEventDescription;
        private ImageView mEventImage;
        private TextView mEventDate;
        private TextView mEventTime;
        private TextView mEventPlace;
        private FloatingActionButton mAddCalendarButton;

        public EventCardViewHolder(View itemView) {
            super(itemView);
            mBinding = DataBindingUtil.bind(itemView);

            mHeaderCard = mBinding.headerCard;
            mClubIcon = mBinding.header.clubLogo;
            mClubName = mBinding.header.clubName;
            mPrivacyLabel = mBinding.header.privacyLabel;
            mEventTitle = mBinding.eventTitle;
            mEventDescription = mBinding.eventDescription;
            mEventImage = mBinding.eventPicture;
            mEventDate = mBinding.footer.date;
            mEventTime = mBinding.footer.time;
            mEventPlace = mBinding.footer.eventPlace;
            mAddCalendarButton = mBinding.footer.addCalendarButton;

            mAddCalendarButton.setOnClickListener(this);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            switch (v.getId()) {
                case R.id.add_calendar_button:
                    mOnClickListener.eventFollowed(v, position, mEvents.get(position).getID());
                default:
                    mOnClickListener.eventClicked(v, position, mEvents.get(position).getID());
            }
        }
    }
}
