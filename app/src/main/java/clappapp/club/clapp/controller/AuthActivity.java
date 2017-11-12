package clappapp.club.clapp.controller;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import clappapp.club.clapp.R;
import clappapp.club.clapp.databinding.ActivityAuthBinding;

public class AuthActivity extends Activity implements View.OnClickListener {

    private ActivityAuthBinding mBinding;
    private EditText mEmail;
    private EditText mPassword;
    private Button mBtnLogin;
    private Button mBtnSignup;
    private Button mBtnGuest;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_auth);
        mEmail = mBinding.emailField;
        mPassword = mBinding.passwordField;
        mBtnLogin = mBinding.loginButton;
        mBtnSignup = mBinding.signupButton;
        mBtnGuest = mBinding.guestButton;
        mAuth = FirebaseAuth.getInstance();

        mBtnLogin.setOnClickListener(this);

        mBtnSignup.setOnClickListener(this);

        mBtnGuest.setOnClickListener(this);
    }

    private void signInWithEmailAndPassword() {
        if (!TextUtils.isEmpty(mEmail.getText())) {
            mAuth.signInWithEmailAndPassword(mEmail.getText().toString(), mPassword.getText().toString())
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                FirebaseUser user = mAuth.getCurrentUser();
                                Toast.makeText(AuthActivity.this, getResources().getString(R.string.no_password_error) + user.getDisplayName() + "!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.login_button:
                signInWithEmailAndPassword();
                intent = new Intent(AuthActivity.this, MainActivity.class);
                break;
            case R.id.signup_button:
                intent = new Intent(AuthActivity.this, CreateAccountActivity.class);
                break;
            case R.id.guest_button:
                intent = new Intent(AuthActivity.this, MainActivity.class);
                break;
        }

        if (intent != null) startActivity(intent);
    }
}
