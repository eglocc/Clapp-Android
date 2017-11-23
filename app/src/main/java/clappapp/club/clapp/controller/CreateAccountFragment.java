package clappapp.club.clapp.controller;


import android.app.DatePickerDialog;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import clappapp.club.clapp.R;
import clappapp.club.clapp.databinding.FragmentCreateAccountFirstBinding;
import clappapp.club.clapp.databinding.FragmentCreateAccountSecondBinding;
import clappapp.club.clapp.model.Enums;
import clappapp.club.clapp.utilities.DataTypeCheckUtils;
import clappapp.club.clapp.utilities.EnumUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class CreateAccountFragment extends Fragment implements DatePickerDialog.OnDateSetListener, View.OnClickListener {

    private static final String TAG = CreateAccountFragment.class.getSimpleName();
    private static final String LAYOUT_ID_TAG = "layoutResourceID";

    interface Callbacks {
        void next(String email, String name, String surname);
        void done(String password, long dob, Enums.Gender gender);
    }

    private static final int[] sGenderIcons = {
            R.drawable.gender_male,
            R.drawable.gender_female,
            R.drawable.lgbt_flag
    };

    //first fragment views
    private TextInputEditText email;
    private TextInputEditText name;
    private TextInputEditText surname;
    private FloatingActionButton forwardButton;

    //second fragment views
    private TextInputEditText mPassword;
    private TextInputEditText mConfirmPassword;
    private EditText mDoBPicker;
    private Spinner mGenderSpinner;
    private FloatingActionButton mDoneButton;

    private int mLayoutResourceID;
    private ViewDataBinding mBinding;
    private Callbacks mCallback;
    private long mDob;

    public CreateAccountFragment() {
        // Required empty public constructor
    }

    public static CreateAccountFragment newInstance(int layoutResourceID) {

        Bundle args = new Bundle();

        args.putInt(LAYOUT_ID_TAG, layoutResourceID);

        CreateAccountFragment fragment = new CreateAccountFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallback = (Callbacks) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement Callbacks.");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLayoutResourceID = getArguments().getInt(LAYOUT_ID_TAG);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, mLayoutResourceID, container, false);
        switch (mLayoutResourceID) {
            case R.layout.fragment_create_account_first:
                initFirstFragment();
                break;
            case R.layout.fragment_create_account_second:
                initSecondFragment();
                break;
        }

        return mBinding.getRoot();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.next_page_button:
                checkFirstStepErrors();
                break;
            case R.id.clapper_dob_picker:
                DatePickerFragment dbfrag = new DatePickerFragment();
                dbfrag.show(getChildFragmentManager(), DatePickerFragment.TAG);
                break;
            case R.id.done_button:
                checkSecondStepErrors();
                break;
        }
    }

    private void initFirstFragment() {
        email = ((FragmentCreateAccountFirstBinding) mBinding).clapperEmail;
        name = ((FragmentCreateAccountFirstBinding) mBinding).clapperName;
        surname = ((FragmentCreateAccountFirstBinding) mBinding).clapperSurname;
        forwardButton = ((FragmentCreateAccountFirstBinding) mBinding).nextPageButton;

        forwardButton.setOnClickListener(this);
    }

    private void initSecondFragment() {

        mPassword = ((FragmentCreateAccountSecondBinding) mBinding).clapperPassword;
        mConfirmPassword = ((FragmentCreateAccountSecondBinding) mBinding).clapperPasswordConfirm;
        mDoBPicker = ((FragmentCreateAccountSecondBinding) mBinding).clapperDobPicker;
        mGenderSpinner = ((FragmentCreateAccountSecondBinding) mBinding).clapperGenderSpinner;
        mDoneButton = ((FragmentCreateAccountSecondBinding) mBinding).doneButton;

        mGenderSpinner.setContentDescription(getResources().getString(R.string.gender));
        CustomSpinnerAdapter adapter = new CustomSpinnerAdapter(getContext(),
                getResources().getStringArray(R.array.gender_types), sGenderIcons);
        mGenderSpinner.setAdapter(adapter);
        mGenderSpinner.setSelection(adapter.getCount());

        mDoBPicker.setOnClickListener(this);
        mDoneButton.setOnClickListener(this);
    }

    private void checkFirstStepErrors() {
        boolean error = false;

        String tEmail = email.getText().toString();
        String tName = name.getText().toString();
        String tSurname = surname.getText().toString();

        if (TextUtils.isEmpty(tEmail)) {
            email.setError(getResources().getString(R.string.no_email_error));
            error = true;
        } else if (!tEmail.contains("@ku.edu.tr")) {
            email.setError(getResources().getString(R.string.email_not_valid_error));
            error = true;
        }

        if (TextUtils.isEmpty(tName)) {
            name.setError(getResources().getString(R.string.no_name_error));
            error = true;
        } else if (name.length() < 2) {
            name.setError(getResources().getString(R.string.name_too_short_error));
            error = true;
        }

        if (TextUtils.isEmpty(tSurname)) {
            surname.setError(getResources().getString(R.string.no_surname_error));
            error = true;
        } else if (surname.length() < 2) {
            surname.setError(getResources().getString(R.string.surname_too_short_error));
            error = true;
        }

        if (!error) {
            mCallback.next(tEmail, tName, tSurname);
        }
    }

    private void checkSecondStepErrors() {
        boolean error = false;

        if (DataTypeCheckUtils.checkPassword(mPassword.getText().toString()) != 1) {
            mPassword.setError(getResources().getString(DataTypeCheckUtils.checkPassword(mPassword.getText().toString())));
            error = true;
        }

        if (!(mConfirmPassword.getText().toString().equals(mPassword.getText().toString()))) {
            mConfirmPassword.setError(getResources().getString(R.string.password_doesnt_match));
            error = true;
        }

        if (mDob == 0) {
            mDoBPicker.setError(getResources().getString(R.string.no_date_of_birth_error));
            error = true;
        }

        if (mGenderSpinner.getSelectedItemPosition() == 0) {

        }

        if (!error) {
            mCallback.done(mPassword.getText().toString(),
                    mDob, EnumUtils.convertStringToGender(mGenderSpinner.getSelectedItem().toString()));
        }
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        Calendar cal = new GregorianCalendar();
        cal.set(year, month, day);
        final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        mDoBPicker.setText(dateFormat.format(cal.getTime()));
        mDob = cal.getTimeInMillis();
    }
}
