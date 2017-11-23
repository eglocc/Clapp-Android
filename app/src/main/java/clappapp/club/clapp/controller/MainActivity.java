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

    private class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {

        private String[] mPageTitles;

        private MyOnPageChangeListener(String[] pageTitles) {
            mPageTitles = pageTitles;
            getSupportActionBar().setTitle(mPageTitles[0]);
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            getSupportActionBar().setTitle(mPageTitles[position]);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    private ActivityMainBinding mBinding;
    private DataHelper mDataHelper;

    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private MainPagerAdapter mPagerAdapter;
    private ArrayList<SoftEvent> mEvents;
    private ArrayList<SoftClub> mClubs;
    private ViewPager.OnPageChangeListener mPageChangeListener;

    private int mPageIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mDataHelper = DataHelper.getInstance();

        mViewPager = mBinding.viewPager;
        mTabLayout = mBinding.tabLayout;

        String[] pageTitles = getResources().getStringArray(R.array.main_activity_page_titles);
        mPageChangeListener = new MyOnPageChangeListener(pageTitles);
        mViewPager.addOnPageChangeListener(mPageChangeListener);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mEvents = mDataHelper.getFakeEvents();
        mClubs = mDataHelper.getFakeClubs();
        mPagerAdapter = new MainPagerAdapter(getSupportFragmentManager(), mEvents, mClubs,
                getString(R.string.no_events),
                getString(R.string.no_clubs));
        mViewPager.setAdapter(mPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        for (int i = 0; i < mPagerAdapter.getCount(); i++) {
            mTabLayout.getTabAt(i).setIcon(sTabIcons[i]);
        }
        mViewPager.setCurrentItem(mPageIndex);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPageIndex = mViewPager.getCurrentItem();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mViewPager.removeOnPageChangeListener(mPageChangeListener);
    }
}
