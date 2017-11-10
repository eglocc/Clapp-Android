package clappapp.club.clapp.controller;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;

import clappapp.club.clapp.R;
import clappapp.club.clapp.databinding.EventCardLayoutBinding;
import clappapp.club.clapp.model.Enums;
import clappapp.club.clapp.model.Event;

public class EventCardFragment extends Fragment {

    private static final String TAG = EventCardFragment.class.getSimpleName();

    private static final String ADD_IMAGE_FRAGMENT_TAG = AddImageFragment.class.getSimpleName();

    private static final HashMap<Enums.Privacy, Integer> sPrivacyMap = new HashMap<>();

    static {
        sPrivacyMap.put(Enums.Privacy.GLOBAL, R.drawable.earth);
        sPrivacyMap.put(Enums.Privacy.LOCAL, R.drawable.school);
        sPrivacyMap.put(Enums.Privacy.CLUB, R.drawable.club_privacy);
        sPrivacyMap.put(Enums.Privacy.GROUP, R.drawable.group_privacy);
        sPrivacyMap.put(Enums.Privacy.PRIVATE, R.drawable.custom_privacy);
    }

    private EventCardLayoutBinding mBinding;
    private Event mEvent;

    //Preview UI
    private ImageView mClubIcon;
    private TextView mClubName;
    private TextView mPrivacyLabel;
    private TextView mEventTitle;
    private TextView mEventDescription;
    private TextView mEventDate;
    private TextView mEventTime;
    private TextView mEventPlace;
    private FloatingActionButton mAddCalendarButton;

    public EventCardFragment() {
        // Required empty public constructor
    }

    public void updateEventCard(Event event) {
        if (event != null) {
            mEvent = event;

            Enums.Privacy privacy = event.getPrivacy();
            mPrivacyLabel.setText(privacy.toString());
            mPrivacyLabel.setCompoundDrawablesWithIntrinsicBounds(sPrivacyMap.get(privacy), 0, 0, 0);
            mEventTitle.setText(event.getTitle());
            mEventDescription.setText(event.getDescription());
            mEventDate.setText(event.getDateString());
            mEventTime.setText(event.getTimeString());
            mEventPlace.setText(event.getPlace());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.event_card_layout, container, false);
        initEventCard(savedInstanceState);
        return mBinding.getRoot();
    }

    private void initEventCard(Bundle savedInstanceState) {
        mClubIcon = mBinding.header.clubLogo;
        mClubName = mBinding.header.clubName;
        mPrivacyLabel = mBinding.header.privacyLabel;
        mEventTitle = mBinding.eventTitle;
        mEventDescription = mBinding.eventDescription;
        mEventDate = mBinding.footer.eventDate;
        mEventTime = mBinding.footer.eventTime;
        mEventPlace = mBinding.footer.eventPlace;
        mAddCalendarButton = mBinding.footer.addCalendarButton;

        updateEventCard(mEvent);

        if (savedInstanceState == null) {
            AddImageFragment fragment = AddImageFragment.newInstance();
            getChildFragmentManager().beginTransaction()
                    .replace(R.id.event_picture_frame, fragment, ADD_IMAGE_FRAGMENT_TAG)
                    .commit();
        }
    }
}
