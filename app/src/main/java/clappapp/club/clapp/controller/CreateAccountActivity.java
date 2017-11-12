package clappapp.club.clapp.controller;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import clappapp.club.clapp.R;
import clappapp.club.clapp.databinding.ActivityCreateAccountBinding;
import clappapp.club.clapp.model.Enums;
import clappapp.club.clapp.model.User;

public class CreateAccountActivity extends AppCompatActivity implements CreateAccountFragment.Callbacks {

    private String mEmail;
    private String mName;
    private String mSurname;
    private String mPassword;
    private Long mDateOfBirth;
    private Enums.Gender mGender;
    private FirebaseAuth mAuth;
    FirebaseDatabase mDatabase;

    private ActivityCreateAccountBinding mBinding;
    private NonSwipeableViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_create_account);
        mViewPager = mBinding.createAccountPager;
        mViewPager.setAdapter(new CreateAccountPagerAdapter(getSupportFragmentManager()));
        getSupportActionBar().hide();
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance();
        mDatabase.getReference("users").setValue(1);
    }

    @Override
    public void next(String email, String name, String surname) {
        mEmail = email;
        mName = name;
        mSurname = surname;
        int currentItem = mViewPager.getCurrentItem();
        mViewPager.setCurrentItem(currentItem + 1);
    }

    public void done(String password, long dob, Enums.Gender gender) {
        mPassword = password;
        mDateOfBirth = dob;
        mGender = gender;
        createAccount();
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

    private void createAccount() {
        mAuth.createUserWithEmailAndPassword(mEmail, mPassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    User user = new User();
                    user.setName(mName);
                    user.setSurname(mSurname);
                    user.setEmail(mEmail);
                    user.setDateOfBirth(mDateOfBirth);
                    user.setGender(mGender);
                    user.setID(mAuth.getCurrentUser().getUid());
                    mDatabase.getReference("users").child(user.getEncodedEmail()).setValue(user)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            mAuth.signInWithEmailAndPassword(mEmail, mPassword)
                                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    Toast.makeText(CreateAccountActivity.this,
                                            getResources().getString(R.string.account_created), Toast.LENGTH_SHORT).show();
                                    onBackPressed();
                                }
                            });
                        }
                    });

                } else {
                    Toast.makeText(CreateAccountActivity.this, getResources().getString(R.string.user_exists_error), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
