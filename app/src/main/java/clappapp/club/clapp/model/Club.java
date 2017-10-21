package clappapp.club.clapp.model;

import java.util.ArrayList;

/**
 * Created by enver on 21.10.2017.
 */

public class Club {
    public Club() {
    }

    ;
    private String mName;
    private Long mID;
    private Long mPresident;
    private ArrayList<Long> mMembers;
    private ArrayList<Long> mClappers;
    private ArrayList<Long> mUpcomingEvents;
    private ArrayList<Long> mPassedEvents;
    private ArrayList<Long> mRequestList;
    //xEventClappers Lists
    private boolean mPublicMemberList;


    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public Long getmID() {
        return mID;
    }

    public void setmID(Long mID) {
        this.mID = mID;
    }

    public Long getmPresident() {
        return mPresident;
    }

    public void setmPresident(Long mPresident) {
        this.mPresident = mPresident;
    }

    public ArrayList<Long> getmMembers() {
        return mMembers;
    }

    public void setmMembers(ArrayList<Long> mMembers) {
        this.mMembers = mMembers;
    }

    public ArrayList<Long> getmClappers() {
        return mClappers;
    }

    public void setmClappers(ArrayList<Long> mClappers) {
        this.mClappers = mClappers;
    }

    public ArrayList<Long> getmUpcomingEvents() {
        return mUpcomingEvents;
    }

    public void setmUpcomingEvents(ArrayList<Long> mUpcomingEvents) {
        this.mUpcomingEvents = mUpcomingEvents;
    }

    public ArrayList<Long> getmPassedEvents() {
        return mPassedEvents;
    }

    public void setmPassedEvents(ArrayList<Long> mPassedEvents) {
        this.mPassedEvents = mPassedEvents;
    }

    public ArrayList<Long> getmRequestList() {
        return mRequestList;
    }

    public void setmRequestList(ArrayList<Long> mRequestList) {
        this.mRequestList = mRequestList;
    }

    public boolean ismPublicMemberList() {
        return mPublicMemberList;
    }

    public void setmPublicMemberList(boolean mPublicMemberList) {
        this.mPublicMemberList = mPublicMemberList;
    }


}


