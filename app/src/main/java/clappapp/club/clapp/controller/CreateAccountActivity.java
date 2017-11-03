package clappapp.club.clapp.controller;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import clappapp.club.clapp.R;
import clappapp.club.clapp.databinding.ActivityCreateAccountBinding;
import clappapp.club.clapp.model.Enums;

public class CreateAccountActivity extends AppCompatActivity implements CreateAccountFragment.onArrowClickListener {

    private String mEmail;
    private String mName;
    private String mSurname;
    private String mPassword;
    private Long mDateOfBirth;
    private Enums.Gender mGender;

    private ActivityCreateAccountBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_create_account);
        mBinding.createAccountPager.setAdapter(new CreateAccountPagerAdapter(getSupportFragmentManager()));
        getSupportActionBar().hide();

    }

    @Override
    public void next(String email, String name, String surname) {
        int currentItem = mBinding.createAccountPager.getCurrentItem();
        mBinding.createAccountPager.setCurrentItem(currentItem + 1);
    }

    @Override
    public void onBackPressed() {
        int currentItem = mBinding.createAccountPager.getCurrentItem();
        if (currentItem == 0) {
            super.onBackPressed();
        } else {
            mBinding.createAccountPager.setCurrentItem(currentItem - 1);
        }
    }
}
