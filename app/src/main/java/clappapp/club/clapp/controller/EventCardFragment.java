package clappapp.club.clapp.controller;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import clappapp.club.clapp.R;
import clappapp.club.clapp.databinding.EventCardLayoutBinding;
import clappapp.club.clapp.model.Event;

public class EventCardFragment extends Fragment {

    private EventCardLayoutBinding mBinding;
    private Event mEvent;

    //Preview UI
    private TextView mEventTitle;
    private TextView mEventDescription;
    private TextView mEventDate;
    private TextView mEventTime;
    private TextView mEventPlace;
    private ImageButton mAddCalendarButton;

    public EventCardFragment() {
        // Required empty public constructor
    }

    public void setEvent(Event event) {
        mEvent = event;

        mEventTitle.setText(mEvent.getTitle());
        mEventDescription.setText(mEvent.getDescription());
        mEventDate.setText(mEvent.getDateString());
        mEventTime.setText(mEvent.getTimeString());
        mEventPlace.setText(mEvent.getPlace());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.event_card_layout, container, false);
        initEventCard();
        return mBinding.getRoot();
    }

    private void initEventCard() {
        mEventTitle = mBinding.eventTitle;
        mEventDescription = mBinding.eventDescription;
        mEventDate = mBinding.eventDate;
        mEventTime = mBinding.eventTime;
        mEventPlace = mBinding.eventPlace;
        mAddCalendarButton = mBinding.addCalendarButton;

        if (mEvent != null) {
            mEventTitle.setText(mEvent.getTitle());
            mEventDescription.setText(mEvent.getDescription());
            mEventDate.setText(mEvent.getDateString());
            mEventTime.setText(mEvent.getTimeString());
            mEventPlace.setText(mEvent.getPlace());
        }
    }
}
