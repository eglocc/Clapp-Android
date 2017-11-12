package clappapp.club.clapp.controller;

import android.databinding.DataBindingUtil;
import android.support.design.widget.FloatingActionButton;
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

    public EventAdapter(EventAdapter.OnClickListener handler, ArrayList<SoftEvent> events) {
        mOnClickListener = handler;
        mEvents = events;
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

            ((EventCardViewHolder) holder).mClubName.setText(event.getClubName());
            ((EventCardViewHolder) holder).mClubIcon.setImageResource(event.getClubIcon());

            Enums.Privacy privacy = event.getPrivacy();
            ((EventCardViewHolder) holder).mPrivacyLabel.setText(privacy.toString());
            ((EventCardViewHolder) holder).mPrivacyLabel.setCompoundDrawablesWithIntrinsicBounds(sPrivacyMap.get(privacy), 0, 0, 0);
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

    public class EventCardViewHolder extends RecyclerView.ViewHolder {

        private EventListItemBinding mBinding;

        //Preview UI
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
