package clappapp.club.clapp.controller;

import android.app.DatePickerDialog;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;

import java.util.Date;

import clappapp.club.clapp.R;
import clappapp.club.clapp.databinding.FragmentCreateAccountFirstBinding;
import clappapp.club.clapp.databinding.FragmentCreateAccountSecondBinding;
import clappapp.club.clapp.model.DataTypeCheck;

/**
 * A simple {@link Fragment} subclass.
 */
public class CreateAccountFragment extends Fragment {

    interface onArrowClickListener {
        void next(String email, String name, String surname);
    }

    //first fragment views
    private TextInputEditText email;
    private TextInputEditText name;
    private TextInputEditText surname;
    private ImageView forwardButton;

    //second fragment views
    private TextInputEditText mPassword;
    private TextInputEditText mConfirmPassword;
    private EditText mDoBPicker;
    private RadioGroup mGenderRadioGroup;

    private int mLayoutResourceId;
    private ViewDataBinding mBinding;
    private onArrowClickListener mCallback;


    public CreateAccountFragment() {
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallback = (onArrowClickListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement onArrowClickListener.");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, mLayoutResourceId, container, false);
        switch (mLayoutResourceId) {
            case R.layout.fragment_create_account_first:
                email = ((FragmentCreateAccountFirstBinding) mBinding).clapperEmailInput;
                name = ((FragmentCreateAccountFirstBinding) mBinding).clapperNameInput;
                surname = ((FragmentCreateAccountFirstBinding) mBinding).clapperSurnameInput;
                forwardButton = ((FragmentCreateAccountFirstBinding) mBinding).nextPageButton;
                break;
            case R.layout.fragment_create_account_second:
                Log.d("as", "s");
                break;
        }

        return mBinding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        switch (mLayoutResourceId) {
            case R.layout.fragment_create_account_first:
                initFirstFragment();
                break;
            case R.layout.fragment_create_account_second:
                initSecondFragment();
                break;
        }

    }

    private void initFirstFragment() {

        forwardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean error = false;

                String tEmail = email.getText().toString();
                String tName = name.getText().toString();
                String tSurname = surname.getText().toString();

                int emailCheck = DataTypeCheck.checkEmail(tEmail);
                int nameCheck = DataTypeCheck.checkName(tName);
                int surnameCheck = DataTypeCheck.checkSurname(tSurname);


                if (emailCheck != 1) {
                    email.setError(getResources().getString(emailCheck));
                    error = true;
                }

                if (nameCheck != 1) {
                    name.setError(getResources().getString(nameCheck));
                    error = true;
                }

                if (surnameCheck != 1) {
                    surname.setError(getResources().getString(surnameCheck));
                    error = true;
                }

                if (!error) {
                    mCallback.next(tEmail, tName, tSurname);

                }
            }
        });
    }

    private void initSecondFragment() {

        Date currentDate = new Date(System.currentTimeMillis());

        DatePickerDialog DobPicker = new DatePickerDialog(getActivity().getBaseContext(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                    }
                }, currentDate.getYear(), currentDate.getMonth()
                , currentDate.getDay());

        mPassword = ((FragmentCreateAccountSecondBinding) mBinding).clapperPasswordInput;
        mConfirmPassword = ((FragmentCreateAccountSecondBinding) mBinding).clapperPasswordConfirmInput;
        mDoBPicker = ((FragmentCreateAccountSecondBinding) mBinding).clapperDobPicker;
        mGenderRadioGroup = ((FragmentCreateAccountSecondBinding) mBinding).clapperGenderRadioGroup;

        mDoBPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }


    public void setLayoutResourceId(int id) {
        this.mLayoutResourceId = id;
    }
}
