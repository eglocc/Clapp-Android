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
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import clappapp.club.clapp.R;
import clappapp.club.clapp.databinding.FragmentCreateEventStep1Binding;
import clappapp.club.clapp.databinding.FragmentCreateEventStep2Binding;
import clappapp.club.clapp.databinding.FragmentCreateEventStep3Binding;
import clappapp.club.clapp.model.SoftUser;

public class CreateEventFragment extends Fragment implements View.OnClickListener, DatePickerDialog.OnDateSetListener,
        TimePickerDialog.OnTimeSetListener, AdapterView.OnItemSelectedListener {

    private static final String TAG = CreateEventFragment.class.getSimpleName();
    private static final String INTERFACE_TAG = Callbacks.class.getSimpleName();
    private static final String CLAPPERS_FRAGMENT_TAG = ClappersFragment.class.getSimpleName();
    private static final String EVENT_CARD_FRAGMENT_TAG = EventCardFragment.class.getSimpleName();
    private static final String LAYOUT_TAG = "layoutID";

    interface Callbacks {
        void nextStep(String title, String type, String privacy, Calendar calendar, String date, String time, String place, String description);
        void nextStep();
        void done();
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

    private static final ArrayList<SoftUser> sMembers = new ArrayList<>();

    static {
        sMembers.add(new SoftUser("Ergiz Gizer"));
        sMembers.add(new SoftUser("Dilara Bozyılan"));
        sMembers.add(new SoftUser("Mert Sağsöz"));
        sMembers.add(new SoftUser("Dilara Ertuğrul"));
        sMembers.add(new SoftUser("Enver Can Kayandan"));
        sMembers.add(new SoftUser("Said Bilal Karslı"));
    }

    private ViewDataBinding mBinding;
    private Callbacks mCallback;
    private Calendar mCalendar;
    private int mLayoutResourceId;

    private FloatingActionButton mActionButton;

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

    //Second step view objects
    private CheckBox mChatCheckBox;


    public CreateEventFragment() {
        // Required empty public constructor
    }

    public static CreateEventFragment newInstance(int layoutResourceID) {

        Bundle args = new Bundle();

        args.putInt(LAYOUT_TAG, layoutResourceID);
        CreateEventFragment fragment = new CreateEventFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private boolean hasError() {
        boolean error = false;

        String title = mTitleEditText.getText().toString();
        String date = mDateEditText.getText().toString();
        String time = mTimeEditText.getText().toString();
        String place = mPlaceEditText.getText().toString();
        String description = mDescriptionEditText.getText().toString();

        if (TextUtils.isEmpty(title)) {
            mTitleEditText.setError("Title is required!");
            error = true;
        }
        if (TextUtils.isEmpty(date)) {
            mDateEditText.setError("You must specify the event's date!");
            error = true;
        }
        if (TextUtils.isEmpty(time)) {
            mTimeEditText.setError("You must specify the event's time!");
            error = true;
        }
        if (TextUtils.isEmpty(place)) {
            mPlaceEditText.setError("You must specify the event's place!");
            error = true;
        }
        if (TextUtils.isEmpty(description)) {
            mDescriptionEditText.setError("Description is required!");
            error = true;
        } else if (description.length() < 10) {
            mDescriptionEditText.setError("Description needs to be longer!");
            error = true;
        }

        boolean isEventTypeValid = mEventTypeSpinner.getSelectedItemPosition() != mEventTypeSpinner.getCount();
        boolean isPrivacyValid = mPrivacySpinner.getSelectedItemPosition() != mPrivacySpinner.getCount();

        if (!isEventTypeValid) {
            ((CustomSpinnerAdapter) mEventTypeSpinner.getAdapter()).setError(mEventTypeSpinner.getSelectedView(), "Error!");
            error = true;
        }
        if (!isPrivacyValid) {
            ((CustomSpinnerAdapter) mPrivacySpinner.getAdapter()).setError(mPrivacySpinner.getSelectedView(), "Error!");
            error = true;
        }

        return error;
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
        setRetainInstance(true);
        mLayoutResourceId = getArguments().getInt(LAYOUT_TAG);
        mCalendar = new GregorianCalendar();
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
            case R.layout.fragment_create_event_step3:
                initThirdStep(savedInstanceState);
                break;
        }

        if (mActionButton != null) mActionButton.setOnClickListener(this);

        return mBinding.getRoot();
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        mCalendar.set(year, month, day);
        final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
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
            case R.id.action_button:
                switch (mLayoutResourceId) {
                    case R.layout.fragment_create_event_step1:
                        if (!hasError()) {
                            mCallback.nextStep(mTitleEditText.getText().toString(),
                                    mEventTypeSpinner.getSelectedItem().toString(),
                                    mPrivacySpinner.getSelectedItem().toString(),
                                    mCalendar,
                                    mDateEditText.getText().toString(),
                                    mTimeEditText.getText().toString(),
                                    mPlaceEditText.getText().toString(),
                                    mDescriptionEditText.getText().toString());
                        }
                        break;
                    case R.layout.fragment_create_event_step2:
                        mCallback.nextStep();
                        break;
                    case R.layout.fragment_create_event_step3:
                        mCallback.done();
                        break;
                    default:
                        throw new IllegalStateException("This fragment is in an illegal state!");
                }
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (parent.equals(mPrivacySpinner) && position == parent.getCount() - 1) {
            //ClappersFragment.newInstance(sMembers, getString(R.string.no_contacts))
            //      .show(getChildFragmentManager(), CLAPPERS_FRAGMENT_TAG);
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
        mActionButton = ((FragmentCreateEventStep1Binding) mBinding).actionButton;

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
        mActionButton = ((FragmentCreateEventStep2Binding) mBinding).actionButton;

        if (savedInstanceState == null) {
            ClappersFragment fragment = ClappersFragment.newInstance(sMembers, getString(R.string.no_contacts));
            getChildFragmentManager().beginTransaction()
                    .replace(R.id.add_contacts_container, fragment, CLAPPERS_FRAGMENT_TAG)
                    .commit();
        }
    }

    private void initThirdStep(Bundle savedInstanceState) {
        mActionButton = ((FragmentCreateEventStep3Binding) mBinding).actionButton;

        if (savedInstanceState == null) {
            EventCardFragment fragment = new EventCardFragment();
            getChildFragmentManager().beginTransaction()
                    .replace(R.id.event_card_container, fragment, EVENT_CARD_FRAGMENT_TAG)
                    .commit();
        }
    }
}
