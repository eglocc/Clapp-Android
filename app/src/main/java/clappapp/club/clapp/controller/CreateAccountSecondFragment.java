package clappapp.club.clapp.controller;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioGroup;

import clappapp.club.clapp.R;
import clappapp.club.clapp.databinding.FragmentCreateAccountSecondBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class CreateAccountSecondFragment extends Fragment {
    private FragmentCreateAccountSecondBinding mBinding;
    private TextInputEditText mPassword;
    private TextInputEditText mConfirmPassword;
    private EditText mDoBPicker;
    private RadioGroup mGenderRadioGroup;


    public CreateAccountSecondFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_create_account_second, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        mPassword = mBinding.clapperPasswordInput;
        mConfirmPassword = mBinding.clapperPasswordConfirmInput;
        mDoBPicker = mBinding.clapperDobPicker;
        mGenderRadioGroup = mBinding.clapperGenderRadioGroup;

        mDoBPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
