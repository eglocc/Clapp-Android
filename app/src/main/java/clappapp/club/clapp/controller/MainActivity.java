package clappapp.club.clapp.controller;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

import clappapp.club.clapp.R;
import clappapp.club.clapp.databinding.ActivityMainBinding;
import clappapp.club.clapp.model.Enums;
import clappapp.club.clapp.model.SoftEvent;

public class MainActivity extends AppCompatActivity {

    private static int[] sTabIcons = {
            R.drawable.earth,
            R.drawable.home,
            R.drawable.magnify,
            R.drawable.calendar_blank,
            R.drawable.account
    };

    private ActivityMainBinding mBinding;

    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private MainPagerAdapter mPagerAdapter;
    private ArrayList<SoftEvent> mEvents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        mEvents = new ArrayList<>();
        mEvents.add(new SoftEvent("LEAP",
                "???????????",
                R.drawable.leap,
                "12/11/17",
                "09:30",
                "KWORKS",
                Enums.EventType.COMPETITION,
                Enums.Privacy.GLOBAL,
                "KU IES",
                R.mipmap.icon_ies));
        mEvents.add(new SoftEvent("Zero To One",
                "????????",
                R.drawable.zerotoone,
                "18/03/2017",
                "10:00",
                "SGKM",
                Enums.EventType.CONFERENCE,
                Enums.Privacy.LOCAL,
                "KU Girişimcilik",
                R.mipmap.ic_launcher_round));
        mEvents.add(new SoftEvent("IES Warm Up Party",
                "????????????????",
                R.drawable.ies_warmup_party,
                "28/09/2017",
                "23:00",
                "????",
                Enums.EventType.PARTY,
                Enums.Privacy.GLOBAL,
                "KU IES",
                R.mipmap.icon_ies));
        mEvents.add(new SoftEvent("KUnvetion 2017",
                "???????????????????",
                R.drawable.winter_is_coming,
                "18/11/2017",
                "11:00",
                "Koç Üniversitesi",
                Enums.EventType.WORKSHOP,
                Enums.Privacy.GLOBAL,
                "KU FRP",
                R.mipmap.icon_frp));

        mViewPager = mBinding.viewPager;
        mTabLayout = mBinding.tabLayout;
        mPagerAdapter = new MainPagerAdapter(getSupportFragmentManager(), mEvents);
        mViewPager.setAdapter(mPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        for (int i = 0; i < mPagerAdapter.getCount(); i++) {
            mTabLayout.getTabAt(i).setIcon(sTabIcons[i]);
        }

    }
}
