package clappapp.club.clapp.controller;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

import clappapp.club.clapp.model.SoftEvent;

public class MainPagerAdapter extends FragmentStatePagerAdapter {

    private ArrayList<SoftEvent> mEvents;

    public MainPagerAdapter(FragmentManager fm, ArrayList<SoftEvent> events) {
        super(fm);
        mEvents = events;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return EventDisplayerFragment.newInstance(mEvents);
            case 1:
                return EventDisplayerFragment.newInstance(mEvents);
            default:
                return EventDisplayerFragment.newInstance(mEvents);
        }
    }

    @Override
    public int getCount() {
        return 5;
    }
}
