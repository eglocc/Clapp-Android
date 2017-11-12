package clappapp.club.clapp.model;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {

    public User() {
    }

    private String mName;
    private String mSurname;
    private String mEmail;
    private Enums.Gender mGender;
    private long mID;
    private long mDateOfBirth;
    private long mUniversityID;
    private String mImageLink;
    private ArrayList<Long> mFollowingClubs;
    private ArrayList<Long> mFollowingEvents;
    private ArrayList<Long> mClappers;
    private long mCreateDateTime;
    //Titles
    private ArrayList<Long> mEventsCreated;
    private ArrayList<Long> mEventsAttended;
    //messageBox

    public User(String name) {
        mName = name;
    }

    public User(String name, String email) {
        mName = name;
        mEmail = email;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public String getSurname() {
        return mSurname;
    }

    public void setSurname(String surname) {
        this.mSurname = surname;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        this.mEmail = email;
    }

    public Enums.Gender getGender() {
        return this.mGender;
    }

    public void setGender(Enums.Gender gender) {
        this.mGender = gender;
    }

    public long getID() {
        return mID;
    }

    public void setID(long ID) {
        this.mID = ID;
    }

    public long getDateOfBirth() {
        return mDateOfBirth;
    }

    public void setDateOfBirth(long dateOfBirth) {
        this.mDateOfBirth = dateOfBirth;
    }

    public long getUniversityID() {
        return mUniversityID;
    }

    public void setUniversityID(long universityID) {
        this.mUniversityID = universityID;
    }

    public String getImageLink() {
        return mImageLink;
    }

    public void setImageLink(String imageLink) {
        this.mImageLink = imageLink;
    }

    public ArrayList<Long> getFollowingClubs() {
        return mFollowingClubs;
    }

    public void setFollowingClubs(ArrayList<Long> followingClubs) {
        this.mFollowingClubs = followingClubs;
    }

    public ArrayList<Long> getFollowingEvents() {
        return mFollowingEvents;
    }

    public void setFollowingEvents(ArrayList<Long> followingEvents) {
        this.mFollowingEvents = followingEvents;
    }

    public ArrayList<Long> getClappers() {
        return mClappers;
    }

    public void setClappers(ArrayList<Long> clappers) {
        this.mClappers = clappers;
    }

    public long getCreateDateTime() {
        return mCreateDateTime;
    }

    public void setCreateDateTime(long createDateTime) {
        this.mCreateDateTime = createDateTime;
    }

    public ArrayList<Long> getEventsCreated() {
        return mEventsCreated;
    }

    public void setEventsCreated(ArrayList<Long> eventsCreated) {
        this.mEventsCreated = eventsCreated;
    }

    public ArrayList<Long> getEventsAttended() {
        return mEventsAttended;
    }

    public void setEventsAttended(ArrayList<Long> eventsAttended) {
        this.mEventsAttended = eventsAttended;
    }
}


