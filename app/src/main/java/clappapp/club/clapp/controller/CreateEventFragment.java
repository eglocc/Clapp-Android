package clappapp.club.clapp.controller;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatSpinner;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import clappapp.club.clapp.R;
import clappapp.club.clapp.databinding.FragmentCreateEventAddImageBinding;
import clappapp.club.clapp.databinding.FragmentCreateEventStep1Binding;
import clappapp.club.clapp.databinding.FragmentCreateEventStep2Binding;

public class CreateEventFragment extends Fragment implements View.OnClickListener, DatePickerDialog.OnDateSetListener,
        TimePickerDialog.OnTimeSetListener, AdapterView.OnItemSelectedListener {

    private static final String TAG = CreateEventFragment.class.getSimpleName();
    private static final String INTERFACE_TAG = Callbacks.class.getSimpleName();
    private static final String CHILD_FRAGMENT_TAG = AddClappersFragment.class.getSimpleName();
    private static final String LAYOUT_TAG = "layoutID";

    interface Callbacks {
        boolean firstStepToSecondStep(String title, String type, Calendar calendar, String date, String time, String place, String description);
        boolean secondStepToLastStep();
        boolean showPreview();
    }

    private static int[] sEventTypeIconResourceIDs = {
            R.drawable.event_type_conference,
            R.drawable.event_type_meeting,
            R.drawable.event_type_competition,
            R.drawable.event_type_party,
            R.drawable.event_type_workshop,
            R.drawable.event_type_trip,
            R.drawable.event_type_nightout,
            R.drawable.event_type_training
    };

    private static int[] sPrivacyIconResourceIds = {
            R.drawable.earth,
            R.drawable.school,
            R.drawable.club_privacy,
            R.drawable.group_privacy,
            R.drawable.custom_privacy
    };

    private ViewDataBinding mBinding;
    private int mLayoutResourceId;
    private Callbacks mCallback;
    private Calendar mCalendar;

    //First step view objects
    private TextInputEditText mTitleEditText;
    private TextInputEditText mDescriptionEditText;
    private AppCompatSpinner mEventTypeSpinner;
    private AppCompatSpinner mPrivacySpinner;
    private EditText mDateEditText;
    private EditText mTimeEditText;
    private TextInputEditText mPlaceEditText;
    private ImageView mDateButton;
    private ImageView mTimeButton;
    private ImageView mPlaceButton;

    //Second step objects
    private View mFragmentContainer;
    private CheckBox mChatCheckBox;
    private FloatingActionButton mAddClapperButton;


    //Third step view objects
    private ImageView mEventImage;
    private Button mEventImageAddButton;

    public CreateEventFragment() {
        // Required empty public constructor
    }

    public static CreateEventFragment newInstance(int layoutResourceId) {

        Bundle args = new Bundle();
        args.putInt(LAYOUT_TAG, layoutResourceId);

        CreateEventFragment fragment = new CreateEventFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallback = (Callbacks) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(TAG + " must implement " + INTERFACE_TAG);
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        setRetainInstance(true);
        mCalendar = new GregorianCalendar();
        mLayoutResourceId = getArguments().getInt(LAYOUT_TAG);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil
                .inflate(inflater, mLayoutResourceId, container, false);
        switch (mLayoutResourceId) {
            case R.layout.fragment_create_event_step1:
                initFirstStep();
                break;
            case R.layout.fragment_create_event_step2:
                initSecondStep(savedInstanceState);
                break;
            case R.layout.fragment_create_event_add_image:
                mEventImage = ((FragmentCreateEventAddImageBinding) mBinding).eventPic;
                mEventImageAddButton = ((FragmentCreateEventAddImageBinding) mBinding).addPicButton;
                break;
        }

        return mBinding.getRoot();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.create_event_next_step:
                switch (mLayoutResourceId) {
                    case R.layout.fragment_create_event_step1:
                        return mCallback.firstStepToSecondStep(mTitleEditText.getText().toString(),
                                mEventTypeSpinner.getSelectedItem().toString(),
                                mCalendar,
                                mDateEditText.getText().toString(),
                                mTimeEditText.getText().toString(),
                                mPlaceEditText.getText().toString(),
                                mDescriptionEditText.getText().toString());
                    case R.layout.fragment_create_event_step2:
                        return mCallback.secondStepToLastStep();
                    case R.layout.fragment_create_event_add_image:
                        return mCallback.showPreview();
                }
            default:
                return true;
        }
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        mCalendar.set(year, month, day);
        final SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, MMM d, yyyy", Locale.getDefault());
        mDateEditText.setText(dateFormat.format(mCalendar.getTime()));
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
        mCalendar.set(Calendar.HOUR_OF_DAY, hour);
        mCalendar.set(Calendar.MINUTE, minute);
        final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
        mTimeEditText.setText(dateFormat.format(mCalendar.getTime()));
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.event_date:
            case R.id.event_date_button:
                DatePickerFragment dateFragment = new DatePickerFragment();
                dateFragment.show(getChildFragmentManager(), DatePickerFragment.TAG);
                break;
            case R.id.event_time:
            case R.id.event_time_button:
                TimePickerFragment timeFragment = new TimePickerFragment();
                timeFragment.show(getChildFragmentManager(), TimePickerFragment.TAG);
                break;
            case R.id.add_clapper_button:
                Fragment fragment = getChildFragmentManager().getFragments().get(0);
                if (fragment instanceof AddClappersFragment) {
                    ((AddClappersFragment) fragment).onClickAddButton();
                }
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (parent.equals(mPrivacySpinner) && position == parent.getCount() - 1) {
            Log.d(TAG, "Custom privacy selected");
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void initFirstStep() {
        mTitleEditText = ((FragmentCreateEventStep1Binding) mBinding).eventTitle;
        mEventTypeSpinner = ((FragmentCreateEventStep1Binding) mBinding).eventTypeSpinner;
        mPrivacySpinner = ((FragmentCreateEventStep1Binding) mBinding).privacySpinner;
        mDateEditText = ((FragmentCreateEventStep1Binding) mBinding).eventDate;
        mTimeEditText = ((FragmentCreateEventStep1Binding) mBinding).eventTime;
        mPlaceEditText = ((FragmentCreateEventStep1Binding) mBinding).eventPlace;
        mDescriptionEditText = ((FragmentCreateEventStep1Binding) mBinding).eventDescription;
        mDateButton = ((FragmentCreateEventStep1Binding) mBinding).eventDateButton;
        mTimeButton = ((FragmentCreateEventStep1Binding) mBinding).eventTimeButton;
        mPlaceButton = ((FragmentCreateEventStep1Binding) mBinding).eventPlaceButton;

        mDateButton.setOnClickListener(this);
        mTimeButton.setOnClickListener(this);
        mDateEditText.setOnClickListener(this);
        mTimeEditText.setOnClickListener(this);

        CustomSpinnerAdapter eventTypeAdapter = new CustomSpinnerAdapter(getContext(),
                getResources().getStringArray(R.array.event_types), sEventTypeIconResourceIDs);
        mEventTypeSpinner.setAdapter(eventTypeAdapter);
        mEventTypeSpinner.setSelection(eventTypeAdapter.getCount());

        CustomSpinnerAdapter privacyTypeAdapter = new CustomSpinnerAdapter(getContext(),
                getResources().getStringArray(R.array.privacy_types), sPrivacyIconResourceIds);
        mPrivacySpinner.setAdapter(privacyTypeAdapter);
        mPrivacySpinner.setSelection(privacyTypeAdapter.getCount());
        mPrivacySpinner.setOnItemSelectedListener(this);
    }

    private void initSecondStep(Bundle savedInstanceState) {
        mChatCheckBox = ((FragmentCreateEventStep2Binding) mBinding).chatCheckBox;
        mFragmentContainer = ((FragmentCreateEventStep2Binding) mBinding).addContactsContainer;
        mAddClapperButton = ((FragmentCreateEventStep2Binding) mBinding).addClapperButton;
        if (savedInstanceState == null) {
            AddClappersFragment fragment =
                    AddClappersFragment.newInstance(getString(R.string.add_contacts_prompt), getString(R.string.no_contacts));
            getChildFragmentManager().beginTransaction().replace(R.id.add_contacts_container, fragment, CHILD_FRAGMENT_TAG).commit();
        }
        mAddClapperButton.setOnClickListener(this);
    }
}
