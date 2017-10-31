package clappapp.club.clapp.controller;

import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import clappapp.club.clapp.R;
import clappapp.club.clapp.model.Enums;

public class CreateAccount extends AppCompatActivity {

    private String mEmail;
    private String mName;
    private String mSurname;
    private String mPassword;
    private Long mDateOfBirth;
    private Enums.Gender mGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        getSupportActionBar().hide();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Fragment first = new CreateAccountFirstFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.create_account_frame, first);
        ft.commit();
    }

    public void setFirstThree(String email, String name, String surname) {
        mEmail = email;
        mName = name;
        mSurname = surname;
    }
}
