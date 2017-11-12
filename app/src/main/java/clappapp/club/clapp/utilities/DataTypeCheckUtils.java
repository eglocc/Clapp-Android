package clappapp.club.clapp.utilities;

import clappapp.club.clapp.R;

public class DataTypeCheckUtils {

    private static int MIN_NAME_LENGTH = 3;
    private static int MIN_PASSWORD_LENGTH = 6;

    public static int checkEmail(String email) {
        if (email.equals("")) {
            return R.string.no_email_error;
        } else if (!email.contains("@ku.edu.tr")) {
            return R.string.email_not_valid_error;
        } else {
            return 1;
        }
    }

    public static int checkName(String name) {
        if (name.length() == 0) {
            return R.string.no_name_error;
        } else if (name.length() < MIN_NAME_LENGTH) {
            return R.string.name_too_short_error;
        } else {
            return 1;
        }
    }

    public static int checkSurname(String surname) {
        if (surname.length() == 0) {
            return R.string.no_surname_error;
        } else if (surname.length() < MIN_NAME_LENGTH) {
            return R.string.surname_too_short_error;
        } else {
            return 1;
        }
    }

    public static int checkPassword(String password) {

        if (containsLetter(password) != 1) {
            return containsLetter(password);
        }

        if (password.length() < MIN_PASSWORD_LENGTH) {
            return R.string.password_too_short_error;
        }

        return 1;
    }

    private static int containsLetter(String password) {
        boolean ret = false;

        for (int i = 0; i < password.length(); i++) {
            if ((password.charAt(i) <= 'Z' && password.charAt(i) >= 'A') || (password.charAt(i) >= 'a' && password.charAt(i) <= 'z')) {
                return 1;
            }
        }
        return R.string.password_no_letters;
    }
}
