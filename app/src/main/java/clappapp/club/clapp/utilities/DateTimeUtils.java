package clappapp.club.clapp.utilities;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class DateTimeUtils {

    public static String convertFromMillisToString(Calendar c) {
        final SimpleDateFormat formatter = new SimpleDateFormat("d/MM/yyyy, HH:mm", Locale.getDefault());
        return formatter.format(c.getTime());
    }
}
