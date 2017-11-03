package clappapp.club.clapp.controller;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import clappapp.club.clapp.R;
import clappapp.club.clapp.databinding.ActivityCreateAccountBinding;
import clappapp.club.clapp.model.Enums;

public class CreateAccountActivity extends AppCompatActivity implements CreateAccountFragment.Callbacks {

    private String mEmail;
    private String mName;
    private String mSurname;
    private String mPassword;
    private Long mDateOfBirth;
    private Enums.Gender mGender;

    private ActivityCreateAccountBinding mBinding;
    private NonSwipeableViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_create_account);
        mViewPager = mBinding.createAccountPager;
        mViewPager.setAdapter(new CreateAccountPagerAdapter(getSupportFragmentManager()));
        getSupportActionBar().hide();
    }

    @Override
    public void next(String email, String name, String surname) {
        int currentItem = mViewPager.getCurrentItem();
        mViewPager.setCurrentItem(currentItem + 1);
    }

    @Override
    public void onBackPressed() {
        int currentItem = mViewPager.getCurrentItem();
        if (currentItem == 0) {
            super.onBackPressed();
        } else {
            mViewPager.setCurrentItem(currentItem - 1);
        }
    }
}
