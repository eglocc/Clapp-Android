package clappapp.club.clapp.controller;


import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import clappapp.club.clapp.R;
import clappapp.club.clapp.databinding.FragmentRecyclerBinding;
import clappapp.club.clapp.databinding.FragmentSearchableRecyclerBinding;
import clappapp.club.clapp.model.SoftUser;

/**
 * A simple {@link Fragment} subclass.
 */
public class ClappersFragment extends DialogFragment implements ClappersAdapter.OnClickListener {

    private static final String TAG = ClappersFragment.class.getSimpleName();
    private static final String LAYOUT_ID_TAG = "layoutID";
    private static final String EMPTY_VIEW_TEXT = "empty_view_text";
    private static final String CLAPPERS_LIST = "clappers_list";

    private ViewDataBinding mBinding;

    private SearchView mSearchView;
    private RecyclerView mClappersRecycler;
    private ClappersAdapter mClappersAdapter;
    private ArrayList<SoftUser> mClappMembers;
    private TextView mEmptyView;
    private String mEmptyViewText;
    private int mLayoutResourceID;

    public ClappersFragment() {
        // Required empty public constructor
    }

    public static ClappersFragment newInstance(ArrayList<SoftUser> clappers, String emptyViewText, int layoutResourceID) {

        Bundle args = new Bundle();
        args.putSerializable(CLAPPERS_LIST, clappers);
        args.putString(EMPTY_VIEW_TEXT, emptyViewText);
        args.putInt(LAYOUT_ID_TAG, layoutResourceID);
        ClappersFragment fragment = new ClappersFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        mEmptyViewText = args.getString(EMPTY_VIEW_TEXT);
        mClappMembers = (ArrayList<SoftUser>) args.getSerializable(CLAPPERS_LIST);
        mLayoutResourceID = args.getInt(LAYOUT_ID_TAG);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, mLayoutResourceID, container, false);

        switch (mLayoutResourceID) {
            case R.layout.fragment_searchable_recycler:
                mSearchView = ((FragmentSearchableRecyclerBinding) mBinding).searchView;
                mClappersRecycler = ((FragmentSearchableRecyclerBinding) mBinding).recyclerView;
                mEmptyView = ((FragmentSearchableRecyclerBinding) mBinding).emptyView;
                break;
            case R.layout.fragment_recycler:
                mClappersRecycler = ((FragmentRecyclerBinding) mBinding).recyclerView;
                mEmptyView = ((FragmentRecyclerBinding) mBinding).emptyView;
                break;
        }

        mEmptyView.setText(mEmptyViewText);

        mClappersRecycler.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mClappersRecycler.setLayoutManager(layoutManager);
        mClappersAdapter = new ClappersAdapter(this, mClappMembers);
        mClappersRecycler.setAdapter(mClappersAdapter);

        if (mClappMembers.size() == 0) showEmptyView();
        else showEventData();

        return mBinding.getRoot();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putSerializable(CLAPPERS_LIST, mClappersAdapter.getClappers());
    }

    public void showEventData() {
        mEmptyView.setVisibility(View.INVISIBLE);
        mClappersRecycler.setVisibility(View.VISIBLE);
    }

    public void showEmptyView() {
        mClappersRecycler.setVisibility(View.INVISIBLE);
        mEmptyView.setVisibility(View.VISIBLE);
    }

    @Override
    public void clapperClicked(View v, int position) {
        if (mLayoutResourceID == R.layout.fragment_searchable_recycler) {
            int visibility = v.getVisibility();
            v.setVisibility(visibility == View.GONE ? View.VISIBLE : View.GONE);
            Log.d(TAG, "Clapper at position " + position + " clicked");
        }
    }
}