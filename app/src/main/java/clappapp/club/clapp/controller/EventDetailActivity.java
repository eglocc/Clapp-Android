package clappapp.club.clapp.controller;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import clappapp.club.clapp.R;
import clappapp.club.clapp.model.DataHelper;
import clappapp.club.clapp.model.SoftEvent;

public class EventDetailActivity extends AppCompatActivity {

    private static final String TAG = EventDetailActivity.class.getSimpleName();
    private static final String FRAGMENT_TAG = SingleEventFragment.class.getSimpleName();
    private static final String EVENT_POSITION_TAG = "event_position";
    private static final String EVENT_ID_TAG = "eventID";

    private DataHelper mDataHelper;
    private SoftEvent mEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mDataHelper = DataHelper.getInstance();
        int position = getIntent().getExtras().getInt(EVENT_POSITION_TAG);
        mEvent = mDataHelper.getFakeEvents().get(position);

        if (mEvent != null) {
            SingleEventFragment fragment = SingleEventFragment.newInstance(mEvent, false);
            getSupportFragmentManager().beginTransaction().replace(R.id.detail_fragment_container, fragment, FRAGMENT_TAG).commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
