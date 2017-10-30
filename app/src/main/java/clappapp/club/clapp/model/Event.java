package clappapp.club.clapp.model;

import java.util.ArrayList;

public class Event {
    public Event() {
    }


    private Long mID;
    private Enums.EventType mType;
    private String mTitle;
    private String mDescription;
    private Long mDateTime;
    private String mPlace;
    private String mMapLink;
    private ArrayList<String> mMapList;
    private ArrayList<Long> mClappers;
    private String mImageLink;
    private Long mClub;
    private Long mCreator;
    private Long mCreateDateTime;
    private boolean mPassed;

    private Enums.Privacy mPrivacy;
    private Long mChatroom;
    private ArrayList<Long> mContacts;

    public Long getmID() {
        return mID;
    }

    public void setmID(Long mID) {
        this.mID = mID;
    }

    public Enums.EventType getmType() {
        return mType;
    }

    public void setmType(Enums.EventType mType) {
        this.mType = mType;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public Long getmDateTime() {
        return mDateTime;
    }

    public void setmDateTime(Long mDateTime) {
        this.mDateTime = mDateTime;
    }

    public String getmPlace() {
        return mPlace;
    }

    public void setmPlace(String mPlace) {
        this.mPlace = mPlace;
    }

    public String getmMapLink() {
        return mMapLink;
    }

    public void setmMapLink(String mMapLink) {
        this.mMapLink = mMapLink;
    }

    public ArrayList<String> getmMapList() {
        return mMapList;
    }

    public void setmMapList(ArrayList<String> mMapList) {
        this.mMapList = mMapList;
    }

    public ArrayList<Long> getmClappers() {
        return mClappers;
    }

    public void setmClappers(ArrayList<Long> mClappers) {
        this.mClappers = mClappers;
    }

    public String getmImageLink() {
        return mImageLink;
    }

    public void setmImageLink(String mImageLink) {
        this.mImageLink = mImageLink;
    }

    public Long getmClub() {
        return mClub;
    }

    public void setmClub(Long mClub) {
        this.mClub = mClub;
    }

    public Long getmCreator() {
        return mCreator;
    }

    public void setmCreator(Long mCreator) {
        this.mCreator = mCreator;
    }

    public Long getmCreateDateTime() {
        return mCreateDateTime;
    }

    public void setmCreateDateTime(Long mCreateDateTime) {
        this.mCreateDateTime = mCreateDateTime;
    }

    public boolean ismPassed() {
        return mPassed;
    }

    public void setmPassed(boolean mPassed) {
        this.mPassed = mPassed;
    }

    public Enums.Privacy getmPrivacy() {
        return mPrivacy;
    }

    public void setmPrivacy(Enums.Privacy mPrivacy) {
        this.mPrivacy = mPrivacy;
    }

    public Long getmChatroom() {
        return mChatroom;
    }

    public void setmChatroom(Long mChatroom) {
        this.mChatroom = mChatroom;
    }

    public ArrayList<Long> getmContacts() {
        return mContacts;
    }

    public void setmContacts(ArrayList<Long> mContacts) {
        this.mContacts = mContacts;
    }


}
