package clappapp.club.clapp.controller;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import clappapp.club.clapp.R;
import clappapp.club.clapp.databinding.FragmentClubDetailBinding;
import clappapp.club.clapp.model.SoftClub;

/**
 * A simple {@link Fragment} subclass.
 */
public class ClubDetailFragment extends Fragment {

    private static final String TAG = ClubDetailFragment.class.getSimpleName();
    private static final String CHILD_FRAGMENT_TAG = EventListFragment.class.getSimpleName();
    private static final String CLUB_TAG = "club";

    private FragmentClubDetailBinding mBinding;
    private SoftClub mClub;

    public ClubDetailFragment() {
        // Required empty public constructor
    }

    public static ClubDetailFragment newInstance(SoftClub club) {

        Bundle args = new Bundle();
        args.putSerializable(CLUB_TAG, club);

        ClubDetailFragment fragment = new ClubDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mClub = (SoftClub) getArguments().getSerializable(CLUB_TAG);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_club_detail, container, false);
        if (mClub != null) {
            mBinding.clubName.setText(mClub.getName());
            mBinding.clubLogo.setImageResource(mClub.getLogoID());

            if (savedInstanceState == null) {
                EventListFragment fragment = EventListFragment.newInstance(mClub.getEvents(), getString(R.string.no_events), false);
                getChildFragmentManager().beginTransaction().replace(R.id.child_fragment_container, fragment, CHILD_FRAGMENT_TAG).commit();
            }
        }
        return mBinding.getRoot();
    }

}
