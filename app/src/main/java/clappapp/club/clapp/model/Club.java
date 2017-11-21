package clappapp.club.clapp.model;

import java.util.ArrayList;

public class Club {

    private String mName;
    /**/private int mLogoID;
    private long mID;
    private long mPresidentID;
    private ArrayList<Long> mMembers;
    private ArrayList<Long> mClappers;
    private ArrayList<Long> mUpcomingEvents;
    private ArrayList<Long> mPassedEvents;
    private ArrayList<Long> mRequestList;
    //xEventClappers Lists
    private boolean mMemberListPublic;

    public Club() {
    }

    public Club(String name, int logoID) {
        mName = name;
        mLogoID = logoID;
    }


    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public Long getID() {
        return mID;
    }

    public void setID(long ID) {
        this.mID = ID;
    }

    public long getPresident() {
        return mPresidentID;
    }

    public void setPresident(long presidentID) {
        this.mPresidentID = presidentID;
    }

    public ArrayList<Long> getMembers() {
        return mMembers;
    }

    public void setMembers(ArrayList<Long> members) {
        this.mMembers = members;
    }

    public ArrayList<Long> getClappers() {
        return mClappers;
    }

    public void setClappers(ArrayList<Long> clappers) {
        this.mClappers = clappers;
    }

    public ArrayList<Long> getUpcomingEvents() {
        return mUpcomingEvents;
    }

    public void setUpcomingEvents(ArrayList<Long> upcomingEvents) {
        this.mUpcomingEvents = upcomingEvents;
    }

    public ArrayList<Long> getPassedEvents() {
        return mPassedEvents;
    }

    public void setPassedEvents(ArrayList<Long> passedEvents) {
        this.mPassedEvents = passedEvents;
    }

    public ArrayList<Long> getRequestList() {
        return mRequestList;
    }

    public void setRequestList(ArrayList<Long> requestList) {
        this.mRequestList = requestList;
    }

    public boolean isPublicMemberList() {
        return mMemberListPublic;
    }

    public void setMemberListPublic(boolean memberListPublic) {
        this.mMemberListPublic = memberListPublic;
    }

    public int getLogoID() {
        return mLogoID;
    }

    public void setLogoID(int logoID) {
        this.mLogoID = logoID;
    }
}


