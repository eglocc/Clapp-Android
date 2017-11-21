package clappapp.club.clapp.controller;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

import clappapp.club.clapp.R;
import clappapp.club.clapp.databinding.ActivityMainBinding;
import clappapp.club.clapp.model.DataHelper;
import clappapp.club.clapp.model.SoftClub;
import clappapp.club.clapp.model.SoftEvent;

public class MainActivity extends AppCompatActivity {

    private static int[] sTabIcons = {
            R.drawable.earth,
            R.drawable.home,
            R.drawable.club_privacy,
            R.drawable.calendar_blank,
            R.drawable.account
    };

    private ActivityMainBinding mBinding;
    private DataHelper mDataHelper;

    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private MainPagerAdapter mPagerAdapter;
    private ArrayList<SoftEvent> mEvents;
    private ArrayList<SoftClub> mClubs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mDataHelper = DataHelper.getInstance();
        mEvents = mDataHelper.getFakeEvents();
        mClubs = mDataHelper.getFakeClubs();

        mViewPager = mBinding.viewPager;
        mTabLayout = mBinding.tabLayout;
        mPagerAdapter = new MainPagerAdapter(getSupportFragmentManager(), mEvents, mClubs);
        mViewPager.setAdapter(mPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        for (int i = 0; i < mPagerAdapter.getCount(); i++) {
            mTabLayout.getTabAt(i).setIcon(sTabIcons[i]);
        }
    }
}
