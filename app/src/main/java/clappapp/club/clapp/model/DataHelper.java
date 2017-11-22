package clappapp.club.clapp.model;

import java.util.ArrayList;
import java.util.HashMap;

import clappapp.club.clapp.R;

public class DataHelper {

    private static final DataHelper ourInstance = new DataHelper();
    private ArrayList<SoftEvent> mFakeEvents;
    private ArrayList<SoftClub> mFakeClubs;
    private ArrayList<SoftUser> mFakeMembers;
    private HashMap<Enums.Privacy, Integer> mPrivacyLogoMap;

    public static DataHelper getInstance() {
        return ourInstance;
    }

    private DataHelper() {
        initFakeEvents();
        initFakeClubs();
        initFakeMembers();
        initPrivacyLogoMap();
    }

    private void initFakeEvents() {
        mFakeEvents = new ArrayList<>();
        String lorem = "Lorem ipsum dolor sit amet, delicatissimi te mea. Ex utamur qualisque.";

        mFakeEvents.add(new SoftEvent("LEAP",
                lorem,
                R.drawable.leap,
                "12/11/17",
                "09:30",
                "KWORKS",
                Enums.EventType.COMPETITION,
                Enums.Privacy.GLOBAL,
                "KU IES",
                R.mipmap.icon_ies));
        mFakeEvents.add(new SoftEvent("Zero To One",
                lorem,
                R.drawable.zerotoone,
                "18/03/2017",
                "10:00",
                "SGKM",
                Enums.EventType.CONFERENCE,
                Enums.Privacy.LOCAL,
                "KU Girişimcilik",
                R.mipmap.ic_launcher_round));
        mFakeEvents.add(new SoftEvent("IES Warm Up Party",
                lorem,
                R.drawable.ies_warmup_party,
                "28/09/2017",
                "23:00",
                "Mitte",
                Enums.EventType.PARTY,
                Enums.Privacy.GLOBAL,
                "KU IES",
                R.mipmap.icon_ies));
        mFakeEvents.add(new SoftEvent("KUnvetion 2017",
                lorem,
                R.drawable.winter_is_coming,
                "18/11/2017",
                "11:00",
                "Koç Üniversitesi",
                Enums.EventType.WORKSHOP,
                Enums.Privacy.GLOBAL,
                "KU FRP",
                R.mipmap.icon_frp));
        mFakeEvents.add(new SoftEvent("Hakan Baş ile Söyleşi",
                lorem,
                R.drawable.hakanbasconference,
                "14/12/2016",
                "17:30",
                "SGKM",
                Enums.EventType.CONFERENCE,
                Enums.Privacy.LOCAL,
                "KU Girişimcilik",
                R.mipmap.ic_launcher_round));
    }

    private void initFakeClubs() {
        mFakeClubs = new ArrayList<>();
        ArrayList<SoftEvent> IESEvents = new ArrayList<>();
        IESEvents.add(mFakeEvents.get(0));
        IESEvents.add(mFakeEvents.get(2));
        ArrayList<SoftEvent> entrepreneurshipEvents = new ArrayList<>();
        entrepreneurshipEvents.add(mFakeEvents.get(1));
        entrepreneurshipEvents.add(mFakeEvents.get(4));
        ArrayList<SoftEvent> frpEvents = new ArrayList<>();
        frpEvents.add(mFakeEvents.get(3));
        mFakeClubs.add(new SoftClub("KU IES", R.mipmap.icon_ies, IESEvents));
        mFakeClubs.add(new SoftClub("KU FRP", R.mipmap.icon_frp, frpEvents));
        mFakeClubs.add(new SoftClub("KU Girişimcilik", R.mipmap.ic_launcher, entrepreneurshipEvents));
        mFakeClubs.add(new SoftClub("KU Marketing", R.mipmap.ic_launcher, new ArrayList<>()));
    }

    private void initFakeMembers() {
        mFakeMembers = new ArrayList<>();
        mFakeMembers.add(new SoftUser("Ergiz Gizer"));
        mFakeMembers.add(new SoftUser("Dilara Bozyılan"));
        mFakeMembers.add(new SoftUser("Mert Sağsöz"));
        mFakeMembers.add(new SoftUser("Dilara Ertuğrul"));
        mFakeMembers.add(new SoftUser("Enver Can Kayandan"));
        mFakeMembers.add(new SoftUser("Said Bilal Karslı"));
    }

    private void initPrivacyLogoMap() {
        mPrivacyLogoMap = new HashMap<>();
        mPrivacyLogoMap.put(Enums.Privacy.GLOBAL, R.drawable.earth);
        mPrivacyLogoMap.put(Enums.Privacy.LOCAL, R.drawable.school);
        mPrivacyLogoMap.put(Enums.Privacy.CLUB, R.drawable.club_privacy);
        mPrivacyLogoMap.put(Enums.Privacy.GROUP, R.drawable.group_privacy);
        mPrivacyLogoMap.put(Enums.Privacy.PRIVATE, R.drawable.custom_privacy);
    }

    public final ArrayList<SoftEvent> getFakeEvents() {
        return mFakeEvents;
    }

    public final ArrayList<SoftClub> getFakeClubs() {
        return mFakeClubs;
    }

    public final ArrayList<SoftUser> getFakeMembers() {
        return mFakeMembers;
    }

    public final HashMap<Enums.Privacy, Integer> getPrivacyLogoMap() {
        return mPrivacyLogoMap;
    }
}
