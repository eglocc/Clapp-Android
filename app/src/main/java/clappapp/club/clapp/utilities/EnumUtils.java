package clappapp.club.clapp.utilities;

import clappapp.club.clapp.model.Enums;

public class EnumUtils {

    public static Enums.EventType convertStringToEventType(String type) {
        if (type.contains(" ")) {
            type = type.replace(" ", "");
        }
        return Enums.EventType.valueOf(type.toUpperCase());
    }

    public static Enums.Privacy convertStringToPrivacy(String privacy) {
        if (privacy.contains(" ")) {
            privacy = privacy.replace(" ", "");
        }
        return Enums.Privacy.valueOf(privacy.toUpperCase());
    }

    public static Enums.Gender convertStringToGender(String gender) {
        if (gender.contains(" ")) {
            gender = gender.replace(" ", "");
        }
        return Enums.Gender.valueOf(gender.toUpperCase());
    }
}
