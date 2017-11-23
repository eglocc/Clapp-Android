package clappapp.club.clapp.model;

import java.io.Serializable;
import java.util.ArrayList;

public class SoftClub extends Club implements Serializable {

    private static long sCount = 0;
    private ArrayList<SoftEvent> mEvents;

    public SoftClub(String name, int logoID, ArrayList<SoftEvent> events) {
        super(name, logoID);
        setID(sCount);
        setEvents(events);
        sCount++;
    }

    public ArrayList<SoftEvent> getEvents() {
        return mEvents;
    }

    public void setEvents(ArrayList<SoftEvent> events) {
        this.mEvents = events;
        for (SoftEvent event : events) {
            event.setClubID(getID());
        }
    }

    @Override
    public String toString() {
        return getName();
    }


}
