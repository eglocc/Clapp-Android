package clappapp.club.clapp.controller;


import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.HashMap;

import clappapp.club.clapp.R;
import clappapp.club.clapp.databinding.FragmentRecyclerBinding;
import clappapp.club.clapp.model.SoftClub;

/**
 * A simple {@link Fragment} subclass.
 */
public class ClubListFragment extends Fragment implements ClubAdapter.OnClickListener {

    private static final String TAG = ClubListFragment.class.getSimpleName();

    private static final String CLUB_LIST_TAG = "club_list";
    private static final String CLUB_ID_TAG = "club_ID";

    private HashMap<Integer, Boolean> mClubFollowerMap;

    private FragmentRecyclerBinding mBinding;
    private SearchView mSearchView;
    private RecyclerView mClubRecycler;
    private ClubAdapter mClubAdapter;
    private ArrayList<SoftClub> mClubs;


    public ClubListFragment() {
        // Required empty public constructor
    }

    public static ClubListFragment newInstance(ArrayList<SoftClub> clubs) {

        Bundle args = new Bundle();
        args.putSerializable(CLUB_LIST_TAG, clubs);
        ClubListFragment fragment = new ClubListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private void initFollowersMap() {
        if (mClubs != null && mClubs.size() != 0) {
            mClubFollowerMap = new HashMap<>();
            for (int i = 0; i < mClubs.size(); i++) {
                mClubFollowerMap.put(i, false);
            }
        }
    }

    private boolean isClubFollowed(int position) {
        return mClubFollowerMap.get(position);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_recycler, container, false);
        mSearchView = mBinding.searchView;
        mClubRecycler = mBinding.recyclerView;
        mClubs = (ArrayList<SoftClub>) getArguments().getSerializable(CLUB_LIST_TAG);
        initFollowersMap();

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
