package clappapp.club.clapp.controller;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.rakshakhegde.stepperindicator.StepperIndicator;

import clappapp.club.clapp.R;
import clappapp.club.clapp.databinding.ActivityCreateEventBinding;
import clappapp.club.clapp.model.SoftEvent;

public class CreateEventActivity extends AppCompatActivity implements CreateEventFragment.Callbacks {

    private static final String TAG = CreateEventActivity.class.getSimpleName();

    ActivityCreateEventBinding mBinding;
    private NonSwipeableViewPager mViewPager;
    private CreateEventPagerAdapter mEventAdapter;
    private StepperIndicator mStepper;
    private SoftEvent mEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_create_event);

        mViewPager = mBinding.createEventPager;
        mStepper = mBinding.stepper;


        mEventAdapter = new CreateEventPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mEventAdapter);

        mStepper.setViewPager(mViewPager);
        mStepper.showStepNumberInstead(true);
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
        finish();
    }

    private void nextPage(int currentPosition) {
        mViewPager.setCurrentItem(currentPosition + 1);
    }

    private void previousPage(int currentPosition) {
        mViewPager.setCurrentItem(currentPosition - 1);
    }
}
