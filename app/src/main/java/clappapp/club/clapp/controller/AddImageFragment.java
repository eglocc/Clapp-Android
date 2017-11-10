package clappapp.club.clapp.controller;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import clappapp.club.clapp.R;
import clappapp.club.clapp.databinding.FragmentAddImageBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddImageFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = AddImageFragment.class.getSimpleName();

    private FragmentAddImageBinding mBinding;

    private ImageView mEventImage;
    private FloatingActionButton mAddImageButton;

    public AddImageFragment() {
        // Required empty public constructor
    }

    public static AddImageFragment newInstance() {

        Bundle args = new Bundle();

        AddImageFragment fragment = new AddImageFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_image, container, false);
        mEventImage = mBinding.eventPicture;
        mAddImageButton = mBinding.addPictureButton;
        mAddImageButton.setOnClickListener(this);
        return mBinding.getRoot();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.add_picture_button) {
            Log.d(TAG, "Add image button clicked");
        }
    }
}
