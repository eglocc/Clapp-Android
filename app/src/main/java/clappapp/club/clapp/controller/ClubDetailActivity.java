package clappapp.club.clapp.controller;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import clappapp.club.clapp.R;
import clappapp.club.clapp.model.DataHelper;
import clappapp.club.clapp.model.SoftClub;

public class ClubDetailActivity extends AppCompatActivity {

    private static final String TAG = ClubDetailActivity.class.getSimpleName();
    private static final String FRAGMENT_TAG = ClubDetailFragment.class.getSimpleName();
    private static final String CLUB_ID_TAG = "club_ID";

    private DataHelper mDataHelper;
    private SoftClub mClub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club_detail);

        mDataHelper = DataHelper.getInstance();
        int position = getIntent().getExtras().getInt(CLUB_ID_TAG);
        mClub = mDataHelper.getFakeClubs().get(position);
        if (mClub != null) {
            ClubDetailFragment fragment = ClubDetailFragment.newInstance(mClub);
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
