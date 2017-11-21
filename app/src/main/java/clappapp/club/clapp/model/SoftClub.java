package clappapp.club.clapp.model;

import java.io.Serializable;
import java.util.ArrayList;

public class SoftClub extends Club implements Serializable {

    private ArrayList<SoftEvent> mEvents;

    public SoftClub(String name, int logoID, ArrayList<SoftEvent> events) {
        super(name, logoID);
        mEvents = events;
    }

    public ArrayList<SoftEvent> getEvents() {
        return mEvents;
    }

    public void setEvents(ArrayList<SoftEvent> events) {
        this.mEvents = events;
    }

    @Override
    public String toString() {
        return getName();
    }
}
