package clappapp.club.clapp.controller;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;

import com.rakshakhegde.stepperindicator.StepperIndicator;

import java.util.Calendar;

import clappapp.club.clapp.R;
import clappapp.club.clapp.databinding.ActivityCreateEventBinding;
import clappapp.club.clapp.model.Event;
import clappapp.club.clapp.utilities.EnumUtils;

public class CreateEventActivity extends AppCompatActivity implements CreateEventFragment.Callbacks {

    private static final String TAG = CreateEventActivity.class.getSimpleName();

    ActivityCreateEventBinding mBinding;
    private NonSwipeableViewPager mViewPager;
    private CreateEventPagerAdapter mEventAdapter;
    private StepperIndicator mStepper;
    private Event mEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_create_event);

        mEventAdapter = new CreateEventPagerAdapter(getSupportFragmentManager());
        mBinding.createEventPager.setAdapter(mEventAdapter);

        mViewPager = mBinding.createEventPager;
        mStepper = mBinding.stepper;

        mStepper.setViewPager(mViewPager);
        mStepper.showStepNumberInstead(true);

        mEvent = new Event();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_create_event, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (mViewPager.getCurrentItem() == 3) {
            menu.findItem(R.id.create_event_next_step).setIcon(R.drawable.ic_done_white_18dp);
        }
        return true;
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
    public boolean firstStepToSecondStep(String title, String type, Calendar calendar, String date, String time, String place, String description) {
        mEvent.setTitle(title);
        mEvent.setType(EnumUtils.convertStringToEventType(type));
        mEvent.setDescription(description);
        mEvent.setDateTime(calendar.getTimeInMillis());
        mEvent.setDateString(date);
        mEvent.setTimeString(time);
        mEvent.setPlace(place);

        nextPage(mViewPager.getCurrentItem());
        return true;
    }

    @Override
    public boolean secondStepToLastStep() {
        nextPage(mViewPager.getCurrentItem());
        return true;
    }

    @Override
    public boolean showPreview() {
        int currentPosition = mViewPager.getCurrentItem();
        Fragment currentFragment = getSupportFragmentManager().getFragments().get(currentPosition);
        if (currentFragment instanceof EventCardFragment) {
            ((EventCardFragment) currentFragment).setEvent(mEvent);
        }
        nextPage(currentPosition);
        return true;
    }

    private void nextPage(int currentPosition) {
        mViewPager.setCurrentItem(currentPosition + 1);
    }

    private void previousPage(int currentPosition) {
        mViewPager.setCurrentItem(currentPosition - 1);
    }
}
