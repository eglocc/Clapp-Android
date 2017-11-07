package clappapp.club.clapp.utilities;

import clappapp.club.clapp.model.Enums;

public class EnumUtils {

    public static Enums.EventType convertStringToEventType(String type) {
        if (type.contains(" ")) {
            type = type.replace(" ", "");
        }
        return Enums.EventType.valueOf(type.toUpperCase());
    }
}
