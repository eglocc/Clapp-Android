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
        CreateAccountFragment fragment = new CreateAccountFragment();
        switch (position) {
            case 0:
                fragment.setLayoutResourceId(R.layout.fragment_create_account_first);
                break;
            case 1:
                fragment.setLayoutResourceId(R.layout.fragment_create_account_second);
                break;
            default:
                return null;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
