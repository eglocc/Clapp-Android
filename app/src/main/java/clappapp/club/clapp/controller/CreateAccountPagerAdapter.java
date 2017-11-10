package clappapp.club.clapp.controller;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import clappapp.club.clapp.R;

public class CreateAccountPagerAdapter extends FragmentStatePagerAdapter {

    public CreateAccountPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        CreateAccountFragment fragment;

        switch (position) {
            case 0:
                fragment = CreateAccountFragment.newInstance(R.layout.fragment_create_account_first);
                break;
            case 1:
                fragment = CreateAccountFragment.newInstance(R.layout.fragment_create_account_second);
                break;
            default:
                fragment = null;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
