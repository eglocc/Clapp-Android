package clappapp.club.clapp.model;

import java.util.ArrayList;

/**
 * Created by enver on 21.10.2017.
 */

public class User {
    public User() {
    }

    ;
    private String mName;
    private String mSurname;
    private String mEmail;
    private long mID;
    private long mDateOfBirth;
    private long mUniversityID;
    private ArrayList<Long> mFollowingClubs;
    private ArrayList<Long> mFollowingEvents;
    private ArrayList<Long> mClappers;
    private Long mCreateDateTime;
    //Titles
    private ArrayList<Long> mEventsCreated;
    private ArrayList<Long> mEventsAttended;
    //messageBox


    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmSurname() {
        return mSurname;
    }

    public void setmSurname(String mSurname) {
        this.mSurname = mSurname;
    }

    public String getmEmail() {
        return mEmail;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public long getmID() {
        return mID;
    }

    public void setmID(long mID) {
        this.mID = mID;
    }

    public long getmDateOfBirth() {
        return mDateOfBirth;
    }

    public void setmDateOfBirth(long mDateOfBirth) {
        this.mDateOfBirth = mDateOfBirth;
    }

    public long getmUniversityID() {
        return mUniversityID;
    }

    public void setmUniversityID(long mUniversityID) {
        this.mUniversityID = mUniversityID;
    }

    public ArrayList<Long> getmFollowingClubs() {
        return mFollowingClubs;
    }

    public void setmFollowingClubs(ArrayList<Long> mFollowingClubs) {
        this.mFollowingClubs = mFollowingClubs;
    }

    public ArrayList<Long> getmFollowingEvents() {
        return mFollowingEvents;
    }

    public void setmFollowingEvents(ArrayList<Long> mFollowingEvents) {
        this.mFollowingEvents = mFollowingEvents;
    }

    public ArrayList<Long> getmClappers() {
        return mClappers;
    }

    public void setmClappers(ArrayList<Long> mClappers) {
        this.mClappers = mClappers;
    }

    public Long getmCreateDateTime() {
        return mCreateDateTime;
    }

    public void setmCreateDateTime(Long mCreateDateTime) {
        this.mCreateDateTime = mCreateDateTime;
    }

    public ArrayList<Long> getmEventsCreated() {
        return mEventsCreated;
    }

    public void setmEventsCreated(ArrayList<Long> mEventsCreated) {
        this.mEventsCreated = mEventsCreated;
    }

    public ArrayList<Long> getmEventsAttended() {
        return mEventsAttended;
    }

    public void setmEventsAttended(ArrayList<Long> mEventsAttended) {
        this.mEventsAttended = mEventsAttended;
    }

}


