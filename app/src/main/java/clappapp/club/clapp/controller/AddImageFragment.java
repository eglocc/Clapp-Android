package clappapp.club.clapp.controller;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.io.IOException;

import clappapp.club.clapp.R;
import clappapp.club.clapp.databinding.FragmentAddImageBinding;
import clappapp.club.clapp.model.SoftEvent;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddImageFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = AddImageFragment.class.getSimpleName();
    private static final String EVENT_TAG = "event";
    private static final int SELECT_IMAGE_REQUEST_CODE = 54;

    private FragmentAddImageBinding mBinding;

    private ImageView mEventImage;
    private FloatingActionButton mAddImageButton;
    private SoftEvent mEvent;

    public AddImageFragment() {
        // Required empty public constructor
    }

    public static AddImageFragment newInstance(SoftEvent event) {

        Bundle args = new Bundle();

        args.putSerializable(EVENT_TAG, event);
        AddImageFragment fragment = new AddImageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public void setEvent(SoftEvent event) {
        this.mEvent = event;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mEvent = (SoftEvent) getArguments().getSerializable(EVENT_TAG);
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SELECT_IMAGE_REQUEST_CODE && resultCode == Activity.RESULT_OK && data != null && data.getData() != null) {
            Uri uri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), uri);
                mEventImage.setImageBitmap(bitmap);
                if (mEvent != null) {
                    mEvent.setImageBitmap(bitmap);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.add_picture_button) {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, getString(R.string.select_image_prompt)), SELECT_IMAGE_REQUEST_CODE);
        }
    }
}
