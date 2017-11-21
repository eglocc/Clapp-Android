package clappapp.club.clapp.controller;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import clappapp.club.clapp.R;
import clappapp.club.clapp.databinding.FragmentRecyclerBinding;
import clappapp.club.clapp.model.SoftUser;

/**
 * A simple {@link Fragment} subclass.
 */
public class ClappersFragment extends DialogFragment implements ClappersAdapter.OnClickListener {

    private static final String TAG = ClappersFragment.class.getSimpleName();

    private static final String EMPTY_VIEW_TEXT = "empty_view_text";

    private static final String CLAPPERS_LIST = "clappers_list";

    private FragmentRecyclerBinding mBinding;

    private SearchView mSearchView;
    private RecyclerView mClappersRecycler;
    private ClappersAdapter mClappersAdapter;
    private ArrayList<SoftUser> mClappMembers;
    private String mEmptyViewText;

    public ClappersFragment() {
        // Required empty public constructor
    }

    public static ClappersFragment newInstance(ArrayList<SoftUser> clappers, String emptyViewText) {

        Bundle args = new Bundle();
        args.putSerializable(CLAPPERS_LIST, clappers);
        args.putString(EMPTY_VIEW_TEXT, emptyViewText);
        ClappersFragment fragment = new ClappersFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_recycler, container, false);
        mSearchView = mBinding.searchView;
        mClappersRecycler = mBinding.recyclerView;
        mEmptyViewText = getArguments().getString(EMPTY_VIEW_TEXT);

        mClappMembers = (ArrayList<SoftUser>) getArguments().getSerializable(CLAPPERS_LIST);

        mClappersRecycler.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mClappersRecycler.setLayoutManager(layoutManager);
        mClappersAdapter = new ClappersAdapter(this, mClappMembers, mEmptyViewText);
        mClappersRecycler.setAdapter(mClappersAdapter);
        return mBinding.getRoot();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putSerializable(CLAPPERS_LIST, mClappersAdapter.getClappers());
    }

    @Override
    public void clapperClicked(View v, int position) {
        int visibility = v.getVisibility();
        v.setVisibility(visibility == View.GONE ? View.VISIBLE : View.GONE);
        Log.d(TAG, "Clapper at position " + position + " clicked");
    }
}