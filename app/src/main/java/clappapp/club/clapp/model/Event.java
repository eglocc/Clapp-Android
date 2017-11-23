package clappapp.club.clapp.model;

import java.util.ArrayList;

public class Event {

    private long mID;
    private Enums.EventType mType;
    private String mTitle;
    private String mDescription;
    private long mDateTime;
    private String mDateString;
    private String mTimeString;
    private String mPlace;
    private String mMapLink;
    private ArrayList<String> mMapList;
    private ArrayList<Long> mClappers;
    private int mImageLink;
    /*!*/private String mClubName;
    /*!*/private int mClubIcon;
    private long mClubID;
    private long mCreatorID;
    private long mCreateDateTime;
    private boolean mPassed;

    private Enums.Privacy mPrivacy;
    private long mChatroomID;
    private ArrayList<Long> mContacts;

    public Event() {
    }

    public Event(String title, String description, int imageLink, String date, String time, String place, Enums.EventType type, Enums.Privacy privacy, String clubName, int clubIcon) {
        mTitle = title;
        mDescription = description;
        mImageLink = imageLink;
        mDateString = date;
        mTimeString = time;
        mPlace = place;
        mType = type;
        mPrivacy = privacy;
        mClubName = clubName;
        mClubIcon = clubIcon;
    }

    public long getID() {
        return mID;
    }

    public void setID(long ID) {
        this.mID = ID;
    }

    public Enums.EventType getType() {
        return mType;
    }

    public void setType(Enums.EventType type) {
        this.mType = type;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        this.mDescription = description;
    }

    public long getDateTime() {
        return mDateTime;
    }

    public void setDateTime(long dateTime) {
        this.mDateTime = dateTime;
    }

    public String getPlace() {
        return mPlace;
    }

    public void setPlace(String place) {
        this.mPlace = place;
    }

    public String getMapLink() {
        return mMapLink;
    }

    public void setMapLink(String mapLink) {
        this.mMapLink = mapLink;
    }

    public ArrayList<String> getMapList() {
        return mMapList;
    }

    public void setMapList(ArrayList<String> mapList) {
        this.mMapList = mapList;
    }

    public ArrayList<Long> getClappers() {
        return mClappers;
    }

    public void setClappers(ArrayList<Long> clappers) {
        this.mClappers = clappers;
    }

    public int getImageLink() {
        return mImageLink;
    }

    public void setImageLink(int imageLink) {
        this.mImageLink = imageLink;
    }

    public long getClubID() {
        return mClubID;
    }

    public void setClubID(long clubID) {
        this.mClubID = clubID;
    }

    public long getCreator() {
        return mCreatorID;
    }

    public void setCreator(long creator) {
        this.mCreatorID = creator;
    }

    public long getCreateDateTime() {
        return mCreateDateTime;
    }

    public void setCreateDateTime(long createDateTime) {
        this.mCreateDateTime = createDateTime;
    }

    public boolean isPassed() {
        return mPassed;
    }

    public void setPassed(boolean passed) {
        this.mPassed = passed;
    }

    public Enums.Privacy getPrivacy() {
        return mPrivacy;
    }

    public void setPrivacy(Enums.Privacy privacy) {
        this.mPrivacy = privacy;
    }

    public long getChatroom() {
        return mChatroomID;
    }

    public void setChatroom(long chatroom) {
        this.mChatroomID = chatroom;
    }

    public ArrayList<Long> getContacts() {
        return mContacts;
    }

    public void setContacts(ArrayList<Long> contacts) {
        this.mContacts = contacts;
    }

    public String getTimeString() {
        return mTimeString;
    }

    public void setTimeString(String timeString) {
        this.mTimeString = timeString;
    }

    public String getDateString() {
        return mDateString;
    }

    public void setDateString(String dateString) {
        this.mDateString = dateString;
    }

    public String getClubName() {
        return mClubName;
    }

    public void setClubName(String clubName) {
        this.mClubName = clubName;
    }

    public int getClubIcon() {
        return mClubIcon;
    }

    public void setClubIcon(int clubIcon) {
        this.mClubIcon = clubIcon;
    }
}
