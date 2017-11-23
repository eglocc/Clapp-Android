package clappapp.club.clapp.controller;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;

import clappapp.club.clapp.R;
import clappapp.club.clapp.databinding.FragmentEventDetailBinding;
import clappapp.club.clapp.databinding.FragmentEventPreviewBinding;
import clappapp.club.clapp.model.DataHelper;
import clappapp.club.clapp.model.Enums;
import clappapp.club.clapp.model.SoftEvent;

public class SingleEventFragment extends Fragment {

    private static final String TAG = SingleEventFragment.class.getSimpleName();
    private static final String EVENT_TAG = "event";
    private static final String LAYOUT_ID_TAG = "layoutID";
    private static final String CLAPPERS_FRAGMENT_TAG = ClappersFragment.class.getSimpleName();
    private static final String ADD_IMAGE_FRAGMENT_TAG = AddImageFragment.class.getSimpleName();

    private ViewDataBinding mBinding;
    private DataHelper mDataHelper;
    private SoftEvent mEvent;
    private int mLayoutResourceID;

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

    public static SingleEventFragment newInstance(int layoutResourceID) {

        Bundle args = new Bundle();

        args.putInt(LAYOUT_ID_TAG, layoutResourceID);
        SingleEventFragment fragment = new SingleEventFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static SingleEventFragment newInstance(SoftEvent event, int layoutResourceID) {

        Bundle args = new Bundle();

        args.putSerializable(EVENT_TAG, event);
        args.putInt(LAYOUT_ID_TAG, layoutResourceID);
        SingleEventFragment fragment = new SingleEventFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public void updateEventCard(SoftEvent event) {
        if (event != null) {
            mEvent = event;
            HashMap<Enums.Privacy, Integer> logoMap = mDataHelper.getPrivacyLogoMap();

            mClubName.setText(event.getClubName());
            mClubIcon.setImageResource(event.getClubIcon());
            Enums.Privacy privacy = event.getPrivacy();
            mPrivacyLabel.setText(privacy.toString());
            mPrivacyLabel.setCompoundDrawablesWithIntrinsicBounds(logoMap.get(privacy), 0, 0, 0);
            mEventTitle.setText(event.getTitle());
            mEventDescription.setText(event.getDescription());
            mEventDate.setText(event.getDateString());
            mEventTime.setText(event.getTimeString());
            mEventPlace.setText(event.getPlace());

            if (mLayoutResourceID == R.layout.fragment_event_detail) {
                ((ImageView) mEventImage).setImageResource(event.getImageLink());
            }

            Fragment childFragment = getChildFragmentManager().findFragmentByTag(ADD_IMAGE_FRAGMENT_TAG);
            if (childFragment instanceof AddImageFragment) {
                ((AddImageFragment) childFragment).setEvent(mEvent);
            }
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDataHelper = DataHelper.getInstance();
        Bundle args = getArguments();
        mLayoutResourceID = args.getInt(LAYOUT_ID_TAG);
        if (args.containsKey(EVENT_TAG)) {
            mEvent = (SoftEvent) args.getSerializable(EVENT_TAG);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, mLayoutResourceID, container, false);
        initEventCard(savedInstanceState);
        return mBinding.getRoot();
    }

    private void initEventCard(Bundle savedInstanceState) {

        switch (mLayoutResourceID) {
            case R.layout.fragment_event_preview:
                mClubIcon = ((FragmentEventPreviewBinding) mBinding).header.clubLogo;
                mClubName = ((FragmentEventPreviewBinding) mBinding).header.clubName;
                mPrivacyLabel = ((FragmentEventPreviewBinding) mBinding).header.privacyLabel;
                mEventTitle = ((FragmentEventPreviewBinding) mBinding).eventTitle;
                mEventDescription = ((FragmentEventPreviewBinding) mBinding).eventDescription;
                mEventImage = ((FragmentEventPreviewBinding) mBinding).eventPicture;
                mEventDate = ((FragmentEventPreviewBinding) mBinding).footer.date;
                mEventTime = ((FragmentEventPreviewBinding) mBinding).footer.time;
                mEventPlace = ((FragmentEventPreviewBinding) mBinding).footer.eventPlace;
                mAddCalendarButton = ((FragmentEventPreviewBinding) mBinding).footer.addCalendarButton;

                if (savedInstanceState == null) {
                    AddImageFragment fragment = AddImageFragment.newInstance(mEvent);
                    getChildFragmentManager().beginTransaction()
                            .replace(R.id.event_picture, fragment, ADD_IMAGE_FRAGMENT_TAG)
                            .commit();
                }
                break;
            case R.layout.fragment_event_detail:
                mClubIcon = ((FragmentEventDetailBinding) mBinding).header.clubLogo;
                mClubName = ((FragmentEventDetailBinding) mBinding).header.clubName;
                mPrivacyLabel = ((FragmentEventDetailBinding) mBinding).header.privacyLabel;
                mEventTitle = ((FragmentEventDetailBinding) mBinding).eventTitle;
                mEventDescription = ((FragmentEventDetailBinding) mBinding).eventDescription;
                mEventImage = ((FragmentEventDetailBinding) mBinding).eventPicture;
                mEventDate = ((FragmentEventDetailBinding) mBinding).footer.date;
                mEventTime = ((FragmentEventDetailBinding) mBinding).footer.time;
                mEventPlace = ((FragmentEventDetailBinding) mBinding).footer.eventPlace;
                mAddCalendarButton = ((FragmentEventDetailBinding) mBinding).footer.addCalendarButton;

                if (savedInstanceState == null) {
                    ClappersFragment fragment = ClappersFragment
                            .newInstance(mDataHelper.getFakeMembers(), getString(R.string.no_contacts), R.layout.fragment_recycler);
                    getChildFragmentManager().beginTransaction()
                            .replace(R.id.contacts_container, fragment, CLAPPERS_FRAGMENT_TAG)
                            .commit();
                }
                break;
        }

        updateEventCard(mEvent);
    }
}
