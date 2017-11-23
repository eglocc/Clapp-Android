package clappapp.club.clapp.model;

import android.graphics.Bitmap;

import java.io.Serializable;

public class SoftEvent extends Event implements Serializable {

    private Bitmap mImageBitmap;

    public SoftEvent() {
    }

    public SoftEvent(String title, String description, int imageLink, String date, String time, String place, Enums.EventType type, Enums.Privacy privacy, String clubName, int clubIcon) {
        super(title, description, imageLink, date, time, place, type, privacy, clubName, clubIcon);
    }

    public Bitmap getImageBitmap() {
        return mImageBitmap;
    }

    public void setImageBitmap(Bitmap imageBitmap) {
        this.mImageBitmap = imageBitmap;
    }

    @Override
    public String toString() {
        return getTitle();
    }
}
