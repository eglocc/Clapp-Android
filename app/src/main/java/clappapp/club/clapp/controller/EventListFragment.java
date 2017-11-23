package clappapp.club.clapp.controller;


import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import clappapp.club.clapp.R;
import clappapp.club.clapp.databinding.FragmentRecyclerBinding;
import clappapp.club.clapp.model.SoftEvent;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventListFragment extends Fragment implements EventAdapter.OnClickListener {

    private static final String TAG = EventListFragment.class.getSimpleName();

    private static final String EVENT_LIST_TAG = "event_list";
    private static final String EMPTY_VIEW_TEXT = "empty_view_text";
    private static final String SHOW_HEADER_TAG = "show_header";
    private static final String CLUB_ID_TAG = "clubID";
    private static final String EVENT_POSITION_TAG = "event_position";
    private static final String EVENT_ID_TAG = "eventID";

    private FragmentRecyclerBinding mBinding;
    private RecyclerView mEventRecycler;
    private EventAdapter mEventAdapter;
    private TextView mEmptyView;
    private String mEmptyViewText;
    private ArrayList<SoftEvent> mEvents;
    private boolean mShowHeader;

    public EventListFragment() {
        // Required empty public constructor
    }

    public static EventListFragment newInstance(ArrayList<SoftEvent> events, String emptyViewText, boolean showHeader) {

        Bundle args = new Bundle();
        args.putSerializable(EVENT_LIST_TAG, events);
        args.putString(EMPTY_VIEW_TEXT, emptyViewText);
        args.putBoolean(SHOW_HEADER_TAG, showHeader);

        EventListFragment fragment = new EventListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        Bundle args = getArguments();
        mEvents = (ArrayList<SoftEvent>) args.getSerializable(EVENT_LIST_TAG);
        mEmptyViewText = args.getString(EMPTY_VIEW_TEXT);
        mShowHeader = args.getBoolean(SHOW_HEADER_TAG);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_recycler, container, false);
        mEventRecycler = mBinding.recyclerView;
        mEmptyView = mBinding.emptyView;

        mEmptyView.setText(mEmptyViewText);

        mEventRecycler.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mEventRecycler.setLayoutManager(layoutManager);
        mEventAdapter = new EventAdapter(this, mEvents, mShowHeader);
        mEventRecycler.setAdapter(mEventAdapter);

        if (mEvents.size() == 0) showEmptyView();
        else showEventData();

        return mBinding.getRoot();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_event_list, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_filter:
                Log.d(TAG, "filter button clicked!");
                return true;
            default:
                return true;
        }
    }

    public void showEventData() {
        mEmptyView.setVisibility(View.INVISIBLE);
        mEventRecycler.setVisibility(View.VISIBLE);
    }

    public void showEmptyView() {
        mEventRecycler.setVisibility(View.INVISIBLE);
        mEmptyView.setVisibility(View.VISIBLE);
    }

    @Override
    public void eventClicked(View v, int position, long id) {
        Intent intent = new Intent(getActivity(), EventDetailActivity.class);
        intent.putExtra(EVENT_POSITION_TAG, position);
        intent.putExtra(EVENT_ID_TAG, id);
        startActivity(intent);
    }

    @Override
    public void eventFollowed(View v, int position, long id) {

    }

    @Override
    public void clubHeaderClicked(long clubID) {
        Intent intent = new Intent(getActivity(), ClubDetailActivity.class);
        intent.putExtra(CLUB_ID_TAG, (int) clubID);
        startActivity(intent);
    }
}
