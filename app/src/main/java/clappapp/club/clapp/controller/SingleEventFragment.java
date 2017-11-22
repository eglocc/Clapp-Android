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
import clappapp.club.clapp.databinding.FragmentEventCardBinding;
import clappapp.club.clapp.model.DataHelper;
import clappapp.club.clapp.model.Enums;
import clappapp.club.clapp.model.SoftEvent;

public class SingleEventFragment extends Fragment {

    private static final String TAG = SingleEventFragment.class.getSimpleName();
    private static final String EVENT_TAG = "event";
    private static final String PREVIEW_TAG = "preview";
    private static final String ADD_IMAGE_FRAGMENT_TAG = AddImageFragment.class.getSimpleName();

    private FragmentEventCardBinding mBinding;
    private DataHelper mDataHelper;
    private SoftEvent mEvent;
    private boolean isPreview;

    //Preview UI
    private ImageView mClubIcon;
    private TextView mClubName;
    private TextView mPrivacyLabel;
    private TextView mEventTitle;
    private TextView mEventDescription;
    private View mEventImage;
    private TextView mEventDate;
    private TextView mEventTime;
    private TextView mEventPlace;
    private FloatingActionButton mAddCalendarButton;

    public SingleEventFragment() {
        // Required empty public constructor
    }

    public static SingleEventFragment newInstance(boolean isPreview) {

        Bundle args = new Bundle();

        args.putBoolean(PREVIEW_TAG, isPreview);
        SingleEventFragment fragment = new SingleEventFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static SingleEventFragment newInstance(SoftEvent event, boolean isPreview) {

        Bundle args = new Bundle();

        args.putSerializable(EVENT_TAG, event);
        args.putBoolean(PREVIEW_TAG, isPreview);
        SingleEventFragment fragment = new SingleEventFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public void updateEventCard(SoftEvent event) {
        if (event != null) {
            mEvent = event;
            HashMap<Enums.Privacy, Integer> logoMap = mDataHelper.getPrivacyLogoMap();

            Enums.Privacy privacy = event.getPrivacy();
            mPrivacyLabel.setText(privacy.toString());
            mPrivacyLabel.setCompoundDrawablesWithIntrinsicBounds(logoMap.get(privacy), 0, 0, 0);
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
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_event_card, container, false);
        initEventCard(savedInstanceState);
        return mBinding.getRoot();
    }

    private void initEventCard(Bundle savedInstanceState) {
        mDataHelper = DataHelper.getInstance();
        isPreview = getArguments().getBoolean(PREVIEW_TAG);

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

        if (getArguments().containsKey(EVENT_TAG)) {
            mEvent = (SoftEvent) getArguments().getSerializable(EVENT_TAG);
        }

        updateEventCard(mEvent);

        if (savedInstanceState == null && isPreview) {
            AddImageFragment fragment = AddImageFragment.newInstance();
            getChildFragmentManager().beginTransaction()
                    .replace(R.id.event_picture, fragment, ADD_IMAGE_FRAGMENT_TAG)
                    .commit();
        } else if (!isPreview) {
            mEventImage.setBackgroundResource(mEvent.getImageLink());
        }
    }
}
