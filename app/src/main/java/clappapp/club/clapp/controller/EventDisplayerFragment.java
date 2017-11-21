package clappapp.club.clapp.controller;


import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import clappapp.club.clapp.R;
import clappapp.club.clapp.databinding.FragmentEventDisplayerBinding;
import clappapp.club.clapp.model.SoftEvent;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventDisplayerFragment extends Fragment implements EventAdapter.OnClickListener {

    private static final String EVENT_LIST_TAG = "event_list";
    private static final String SHOW_HEADER_TAG = "show_header";

    private FragmentEventDisplayerBinding mBinding;
    private RecyclerView mEventRecycler;
    private EventAdapter mEventAdapter;
    private TextView mEmptyView;
    private ArrayList<SoftEvent> mEvents;
    private boolean mShowHeader;

    public EventDisplayerFragment() {
        // Required empty public constructor
    }

    public static EventDisplayerFragment newInstance(ArrayList<SoftEvent> events, boolean showHeader) {

        Bundle args = new Bundle();
        args.putSerializable(EVENT_LIST_TAG, events);
        args.putBoolean(SHOW_HEADER_TAG, showHeader);

        EventDisplayerFragment fragment = new EventDisplayerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_event_displayer, container, false);
        mEventRecycler = mBinding.eventRecycler;
        mEmptyView = mBinding.emptyView;

        mEvents = (ArrayList<SoftEvent>) getArguments().getSerializable(EVENT_LIST_TAG);
        mShowHeader = getArguments().getBoolean(SHOW_HEADER_TAG);

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
        inflater.inflate(R.menu.menu_main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_create:
                Intent intent = new Intent(getContext(), CreateEventActivity.class);
                getContext().startActivity(intent);
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

    }
}
