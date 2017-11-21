package clappapp.club.clapp.controller;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

import clappapp.club.clapp.model.SoftClub;
import clappapp.club.clapp.model.SoftEvent;

public class MainPagerAdapter extends FragmentStatePagerAdapter {

    private ArrayList<SoftEvent> mEvents;
    private ArrayList<SoftClub> mClubs;

    public MainPagerAdapter(FragmentManager fm, ArrayList<SoftEvent> events, ArrayList<SoftClub> clubs) {
        super(fm);
        mEvents = events;
        mClubs = clubs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return EventDisplayerFragment.newInstance(mEvents, true);
            case 1:
                return EventDisplayerFragment.newInstance(mEvents, true);
            case 2:
                return ClubListFragment.newInstance(mClubs);
            default:
                return EventDisplayerFragment.newInstance(mEvents, true);
        }
    }

    @Override
    public int getCount() {
        return 5;
    }
}
