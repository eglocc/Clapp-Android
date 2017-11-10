package clappapp.club.clapp.controller;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import clappapp.club.clapp.R;

public class CreateEventPagerAdapter extends FragmentStatePagerAdapter {

    public CreateEventPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = CreateEventFragment.newInstance(R.layout.fragment_create_event_step1);
                return fragment;
            case 1:
                fragment = CreateEventFragment.newInstance(R.layout.fragment_create_event_step2);
                return fragment;
            case 2:
                fragment = CreateEventFragment.newInstance(R.layout.fragment_create_event_step3);
                return fragment;
            default:
                return fragment;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
