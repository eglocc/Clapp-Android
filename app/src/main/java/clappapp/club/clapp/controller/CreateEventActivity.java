package clappapp.club.clapp.controller;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.rakshakhegde.stepperindicator.StepperIndicator;

import clappapp.club.clapp.R;
import clappapp.club.clapp.databinding.ActivityCreateEventBinding;
import clappapp.club.clapp.model.DataHelper;
import clappapp.club.clapp.model.SoftClub;
import clappapp.club.clapp.model.SoftEvent;

public class CreateEventActivity extends AppCompatActivity implements CreateEventFragment.Callbacks {

    private static final String TAG = CreateEventActivity.class.getSimpleName();
    private static final String CLUB_ID_TAG = "clubID";

    private ActivityCreateEventBinding mBinding;
    private DataHelper mDataHelper;
    private NonSwipeableViewPager mViewPager;
    private CreateEventPagerAdapter mEventAdapter;
    private StepperIndicator mStepper;
    private SoftEvent mEvent;
    private String[] mPageTitles;
    private long mClubID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_create_event);
        mDataHelper = DataHelper.getInstance();

        Bundle extras = getIntent().getExtras();
        if (extras != null && extras.containsKey(CLUB_ID_TAG)) {
            mClubID = extras.getLong(CLUB_ID_TAG);
        }

        mViewPager = mBinding.createEventPager;
        mStepper = mBinding.stepper;

        mEventAdapter = new CreateEventPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mEventAdapter);

        mStepper.setViewPager(mViewPager);
        mStepper.showStepNumberInstead(true);

        mPageTitles = getResources().getStringArray(R.array.create_event_page_titles);
        getSupportActionBar().setTitle(mPageTitles[mViewPager.getCurrentItem()]);
    }

    @Override
    public void onBackPressed() {
        int currentItem = mViewPager.getCurrentItem();
        if (currentItem == 0) {
            super.onBackPressed();
        } else {
            previousPage(mViewPager.getCurrentItem());
        }
    }

    @Override
    public void nextStep(SoftEvent event) {
        mEvent = event;
        mEvent.setClubID(mClubID);
        SoftClub club = mDataHelper.getFakeClubs().get((int) mClubID);
        mEvent.setClubName(club.getName());
        mEvent.setClubIcon(club.getLogoID());
        club.getEvents().add(mEvent);
        nextPage(mViewPager.getCurrentItem());
    }

    @Override
    public void nextStep() {
        int currentPosition = mViewPager.getCurrentItem();
        Fragment fragment = getSupportFragmentManager().getFragments().get(currentPosition + 1);
        Fragment childFragment = fragment.getChildFragmentManager().findFragmentByTag(SingleEventFragment.class.getSimpleName());
        if (childFragment instanceof SingleEventFragment) {
            ((SingleEventFragment) childFragment).updateEventCard(mEvent);
        }
        nextPage(currentPosition);
    }

    @Override
    public void done() {
        mDataHelper.getFakeEvents().add(mEvent);
        finish();
    }

    private void nextPage(int currentPosition) {
        mViewPager.setCurrentItem(currentPosition + 1);
        getSupportActionBar().setTitle(mPageTitles[currentPosition + 1]);
    }

    private void previousPage(int currentPosition) {
        mViewPager.setCurrentItem(currentPosition - 1);
        getSupportActionBar().setTitle(mPageTitles[currentPosition - 1]);
    }
}
