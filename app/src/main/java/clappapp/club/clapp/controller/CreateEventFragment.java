package clappapp.club.clapp.controller;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import clappapp.club.clapp.R;
import clappapp.club.clapp.databinding.FragmentCreateEventAddImageBinding;
import clappapp.club.clapp.databinding.FragmentCreateEventStep1Binding;
import clappapp.club.clapp.databinding.FragmentCreateEventStep2Binding;

public class CreateEventFragment extends Fragment implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    private static final String TAG = CreateEventFragment.class.getSimpleName();

    interface Callbacks {
        boolean next(String title, String type, long dateTime, String place, String description);
    }

    private ViewDataBinding mBinding;
    private int mLayoutResourceId;
    private Callbacks mCallback;
    private Calendar mCalendar;

    //First step view objects
    private TextInputEditText mTitleEditText;
    private TextInputEditText mDescriptionEditText;
    private Spinner mEventTypeSpinner;
    private EditText mDateEditText;
    private EditText mTimeEditText;
    private TextInputEditText mPlaceEditText;
    private ImageView mDateButton;
    private ImageView mTimeButton;
    private ImageView mPlaceButton;

    //Second step view objects
    private CheckBox mChatCheckBox;

    //Third step view objects
    private ImageView mEventImage;
    private Button mEventImageAddButton;

    public CreateEventFragment() {
        // Required empty public constructor
    }

    public void setLayoutResourceId(int id) {
        mLayoutResourceId = id;
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
        setHasOptionsMenu(true);
        setRetainInstance(true);
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
                mChatCheckBox = ((FragmentCreateEventStep2Binding) mBinding).chatCheckBox;
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
                return mCallback.next(mTitleEditText.getText().toString(),
                        mEventTypeSpinner.getSelectedItem().toString(),
                        mCalendar.getTimeInMillis(),
                        mPlaceEditText.getText().toString(),
                        mDescriptionEditText.getText().toString());
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

    private void initFirstStep() {
        mTitleEditText = ((FragmentCreateEventStep1Binding) mBinding).eventTitle;
        mEventTypeSpinner = ((FragmentCreateEventStep1Binding) mBinding).eventTypeSpinner;
        mDateEditText = ((FragmentCreateEventStep1Binding) mBinding).eventDate;
        mTimeEditText = ((FragmentCreateEventStep1Binding) mBinding).eventTime;
        mPlaceEditText = ((FragmentCreateEventStep1Binding) mBinding).eventPlace;
        mDescriptionEditText = ((FragmentCreateEventStep1Binding) mBinding).eventDescription;
        mDateButton = ((FragmentCreateEventStep1Binding) mBinding).eventDateButton;
        mTimeButton = ((FragmentCreateEventStep1Binding) mBinding).eventTimeButton;
        mPlaceButton = ((FragmentCreateEventStep1Binding) mBinding).eventPlaceButton;

        mDateButton.setOnClickListener((view) -> {
            DatePickerFragment fragment = new DatePickerFragment();
            fragment.show(getChildFragmentManager(), DatePickerFragment.TAG);
        });

        mTimeButton.setOnClickListener((view) -> {
            TimePickerFragment fragment = new TimePickerFragment();
            fragment.show(getChildFragmentManager(), TimePickerFragment.TAG);
        });
    }
}
