package clappapp.club.clapp.controller;


import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import clappapp.club.clapp.R;
import clappapp.club.clapp.databinding.FragmentSearchableRecyclerBinding;
import clappapp.club.clapp.model.SoftClub;

/**
 * A simple {@link Fragment} subclass.
 */
public class ClubListFragment extends Fragment implements ClubAdapter.OnClickListener {

    private static final String TAG = ClubListFragment.class.getSimpleName();
    private static final String CLUB_LIST_TAG = "club_list";
    private static final String CLUB_ID_TAG = "clubID";
    private static final String EMPTY_VIEW_TEXT = "empty_view_text";

    private SparseBooleanArray mClubFollowerMap;

    private FragmentSearchableRecyclerBinding mBinding;
    private SearchView mSearchView;
    private RecyclerView mClubRecycler;
    private TextView mEmptyView;
    private String mEmptyViewText;
    private ClubAdapter mClubAdapter;
    private ArrayList<SoftClub> mClubs;


    public ClubListFragment() {
        // Required empty public constructor
    }

    public static ClubListFragment newInstance(ArrayList<SoftClub> clubs, String emptyViewText) {

        Bundle args = new Bundle();
        args.putSerializable(CLUB_LIST_TAG, clubs);
        args.putString(EMPTY_VIEW_TEXT, emptyViewText);
        ClubListFragment fragment = new ClubListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private void initFollowersMap() {
        if (mClubs != null && mClubs.size() != 0) {
            mClubFollowerMap = new SparseBooleanArray();
            for (int i = 0; i < mClubs.size(); i++) {
                mClubFollowerMap.put(i, false);
            }
        }
    }

    private boolean isClubFollowed(int position) {
        return mClubFollowerMap.get(position);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        mClubs = (ArrayList<SoftClub>) args.getSerializable(CLUB_LIST_TAG);
        mEmptyViewText = args.getString(EMPTY_VIEW_TEXT);
        initFollowersMap();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_searchable_recycler, container, false);
        mSearchView = mBinding.searchView;
        mClubRecycler = mBinding.recyclerView;
        mEmptyView = mBinding.emptyView;

        mEmptyView.setText(mEmptyViewText);

        mClubRecycler.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mClubRecycler.setLayoutManager(layoutManager);
        mClubAdapter = new ClubAdapter(this, mClubs);
        mClubRecycler.setAdapter(mClubAdapter);

        return mBinding.getRoot();
    }

    @Override
    public void clubFollowed(ImageView v, int position) {
        v.setImageResource(isClubFollowed(position) ? R.drawable.plus_box_outline : R.drawable.tick_black);
        mClubFollowerMap.put(position, !isClubFollowed(position));
    }

    @Override
    public void clubClicked(int position) {
        Intent intent = new Intent(getActivity(), ClubDetailActivity.class);
        intent.putExtra(CLUB_ID_TAG, position);
        startActivity(intent);
    }
}
