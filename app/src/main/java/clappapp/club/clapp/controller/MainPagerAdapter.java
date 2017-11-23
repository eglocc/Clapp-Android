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
    private String mEventEmptyViewText;
    private String mClubEmptyViewText;

    public MainPagerAdapter(FragmentManager fm, ArrayList<SoftEvent> events, ArrayList<SoftClub> clubs, String eventEmptyView, String clubEmptyView) {
        super(fm);
        mEvents = events;
        mClubs = clubs;
        mEventEmptyViewText = eventEmptyView;
        mClubEmptyViewText = clubEmptyView;
    }

    public void setEvents(ArrayList<SoftEvent> events) {
        this.mEvents = events;
        notifyDataSetChanged();
    }

    public void setClubs(ArrayList<SoftClub> clubs) {
        this.mClubs = clubs;
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return EventListFragment.newInstance(mEvents, mEventEmptyViewText, true);
            case 1:
                return EventListFragment.newInstance(mEvents, mEventEmptyViewText, true);
            case 2:
                return ClubListFragment.newInstance(mClubs, mClubEmptyViewText);
            default:
                return EventListFragment.newInstance(mEvents, mEventEmptyViewText, true);
        }
    }

    @Override
    public int getCount() {
        return 5;
    }
}
