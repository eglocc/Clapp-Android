package clappapp.club.clapp.controller;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class CreateEventFragment extends Fragment {

    private ViewDataBinding mBinding;
    private int mLayoutResourceId;

    public CreateEventFragment() {
        // Required empty public constructor
    }

    public void setLayout(int id) {
        mLayoutResourceId = id;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil
                .inflate(inflater, mLayoutResourceId, container, false);
        return mBinding.getRoot();
    }

}
