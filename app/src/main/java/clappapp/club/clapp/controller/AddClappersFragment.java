package clappapp.club.clapp.controller;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import clappapp.club.clapp.R;
import clappapp.club.clapp.databinding.FragmentAddClappersBinding;
import clappapp.club.clapp.model.SoftUser;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddClappersFragment extends Fragment implements AddClappersAdapter.OnClickListener {

    private static final String TAG = AddClappersFragment.class.getSimpleName();

    private static final String PROMPT_TEXT = "prompt_text";
    private static final String EMPTY_VIEW_TEXT = "empty_view_text";

    private static final String CLAPPERS_LIST = "clappers_array";

    private FragmentAddClappersBinding mBinding;

    private RecyclerView mClappersRecycler;
    private AddClappersAdapter mClappersAdapter;
    private ArrayList<SoftUser> mClappers;

    public AddClappersFragment() {
        // Required empty public constructor
    }

    public static AddClappersFragment newInstance(String promptText, String emptyViewText) {

        Bundle args = new Bundle();
        args.putString(PROMPT_TEXT, promptText);
        args.putString(EMPTY_VIEW_TEXT, emptyViewText);
        AddClappersFragment fragment = new AddClappersFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_clappers, container, false);

        mClappersRecycler = mBinding.clappersRecycler;
        String promptText = getArguments().getString(PROMPT_TEXT);
        String emptyViewText = getArguments().getString(EMPTY_VIEW_TEXT);

        if (savedInstanceState == null) {
            mClappers = new ArrayList<>();
        } else {
            mClappers = (ArrayList<SoftUser>) savedInstanceState.getSerializable(CLAPPERS_LIST);
        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mClappersRecycler.setLayoutManager(layoutManager);
        mClappersAdapter = new AddClappersAdapter(this, mClappers, promptText, emptyViewText);
        mClappersRecycler.setAdapter(mClappersAdapter);

        return mBinding.getRoot();
    }

    @Override
    public void onClickRemoveButton(int position) {
        if (mClappersAdapter != null) {
            mClappersAdapter.removeContact(position);
        }
    }

    @Override
    public void onClickAddButton() {
        if (mClappersAdapter != null) {
            mClappersAdapter.addContact();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putSerializable(CLAPPERS_LIST, mClappersAdapter.getClappers());
    }
}