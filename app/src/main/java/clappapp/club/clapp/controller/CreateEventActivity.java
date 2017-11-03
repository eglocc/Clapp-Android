package clappapp.club.clapp.controller;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;

import com.rakshakhegde.stepperindicator.StepperIndicator;

import clappapp.club.clapp.R;
import clappapp.club.clapp.databinding.ActivityCreateEventBinding;
import clappapp.club.clapp.model.Enums;
import clappapp.club.clapp.model.Event;

public class CreateEventActivity extends AppCompatActivity implements CreateEventFragment.Callbacks {

    private static final String TAG = CreateEventActivity.class.getSimpleName();

    ActivityCreateEventBinding mBinding;
    private NonSwipeableViewPager mViewPager;
    private StepperIndicator mStepper;
    private Event mEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_create_event);
        mViewPager = mBinding.createEventPager;
        mStepper = mBinding.stepper;
        mViewPager.setAdapter(new CreateEventPagerAdapter(getSupportFragmentManager()));
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
    public void onBackPressed() {
        int currentItem = mViewPager.getCurrentItem();
        if (currentItem == 0) {
            super.onBackPressed();
        } else {
            mViewPager.setCurrentItem(currentItem - 1);
        }
    }

    @Override
    public boolean next(String title, String type, long dateTime, String place, String description) {
        mEvent.setmTitle(title);
        mEvent.setmType(Enums.EventType.valueOf(type.toUpperCase()));
        mEvent.setmDescription(description);
        mEvent.setmDateTime(dateTime);
        mEvent.setmPlace(place);

        int currentItem = mViewPager.getCurrentItem();
        mViewPager.setCurrentItem(currentItem + 1);
        return true;
    }
}
