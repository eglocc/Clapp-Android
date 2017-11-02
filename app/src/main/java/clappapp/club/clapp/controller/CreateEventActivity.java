package clappapp.club.clapp.controller;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import clappapp.club.clapp.R;
import clappapp.club.clapp.databinding.ActivityCreateEventBinding;

public class CreateEventActivity extends AppCompatActivity {

    ActivityCreateEventBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_create_event);
        mBinding.viewPager.setAdapter(new CreateEventPagerAdapter(getSupportFragmentManager()));
        //mBinding.tabLayout.post(() -> mBinding.tabLayout.setupWithViewPager(mBinding.viewPager));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_create_event, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.create_event_next_step:
                int currentItem = mBinding.viewPager.getCurrentItem();
                mBinding.viewPager.setCurrentItem(currentItem + 1);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        int currentItem = mBinding.viewPager.getCurrentItem();
        if (currentItem == 0) {
            super.onBackPressed();
        } else {
            mBinding.viewPager.setCurrentItem(currentItem - 1);
        }
    }

}
