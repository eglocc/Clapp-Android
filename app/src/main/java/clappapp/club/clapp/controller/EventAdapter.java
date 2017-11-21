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
import clappapp.club.clapp.model.Enums;
import clappapp.club.clapp.model.SoftEvent;

public class EventAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = EventAdapter.class.getSimpleName();

    private static final HashMap<Enums.Privacy, Integer> sPrivacyMap = new HashMap<>();

    static {
        sPrivacyMap.put(Enums.Privacy.GLOBAL, R.drawable.earth);
        sPrivacyMap.put(Enums.Privacy.LOCAL, R.drawable.school);
        sPrivacyMap.put(Enums.Privacy.CLUB, R.drawable.club_privacy);
        sPrivacyMap.put(Enums.Privacy.GROUP, R.drawable.group_privacy);
        sPrivacyMap.put(Enums.Privacy.PRIVATE, R.drawable.custom_privacy);
    }

    interface OnClickListener {
        void eventClicked(View v, int position, long id);
    }

    private final EventAdapter.OnClickListener mOnClickListener;
    private ArrayList<SoftEvent> mEvents;
    private boolean mShowHeader;

    public EventAdapter(EventAdapter.OnClickListener onClickListener, ArrayList<SoftEvent> events, boolean showHeader) {
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
        return new EventCardWithHeaderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof EventCardWithHeaderViewHolder) {
            SoftEvent event = getItem(position);

            if (mShowHeader) {
                ((EventCardWithHeaderViewHolder) holder).mClubName.setText(event.getClubName());
                ((EventCardWithHeaderViewHolder) holder).mClubIcon.setImageResource(event.getClubIcon());
                Enums.Privacy privacy = event.getPrivacy();
                ((EventCardWithHeaderViewHolder) holder).mPrivacyLabel.setText(privacy.toString());
                ((EventCardWithHeaderViewHolder) holder).mPrivacyLabel.setCompoundDrawablesWithIntrinsicBounds(sPrivacyMap.get(privacy), 0, 0, 0);
            } else {
                ((EventCardWithHeaderViewHolder) holder).mHeaderCard.setVisibility(View.GONE);
            }
            ((EventCardWithHeaderViewHolder) holder).mEventTitle.setText(event.getTitle());
            ((EventCardWithHeaderViewHolder) holder).mEventDescription.setText(event.getDescription());
            ((EventCardWithHeaderViewHolder) holder).mEventImage.setImageResource(event.getImageLink());
            ((EventCardWithHeaderViewHolder) holder).mEventDate.setText(event.getDateString());
            ((EventCardWithHeaderViewHolder) holder).mEventTime.setText(event.getTimeString());
            ((EventCardWithHeaderViewHolder) holder).mEventPlace.setText(event.getPlace());
        }
    }

    @Override
    public int getItemCount() {
        return mEvents == null ? 0 : mEvents.size();
    }

    private SoftEvent getItem(int position) {
        return mEvents.get(position);
    }

    public class EventCardWithHeaderViewHolder extends RecyclerView.ViewHolder {

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

        public EventCardWithHeaderViewHolder(View itemView) {
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
        }
    }
}
