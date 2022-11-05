package com.dont4get.utils;

import android.util.Log;

import com.dont4get.ApplicationConstants;

import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    public static final String TAG = Utils.class.getSimpleName();

    /**
     * This function receives a string and returns whether or not this string is a valid email address
     * @param email string
     * @return boolean value that indicates whether the email string is a valid email address ot not.
     */
    public static boolean isValidEmailAddress(String email) {
        boolean result = false;

        if (email == null || email.isEmpty()) {
            return false;
        }

        try {
            Pattern email_pattern = Pattern.compile(ApplicationConstants.EMAIL_REGEX, Pattern.CASE_INSENSITIVE);
            Matcher matcher = email_pattern.matcher(email);
            result = matcher.find();
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }

        return result;

    }


    /**
     * This function receives a string and returns whether or not this string is a valid password.
     * A valid password contains any alpha-numeric character, specials characters and contains six to 20 characters.
     *
     * @param password string
     * @return boolean value that indicates whether or not the password string is valid.
     */
    public static boolean isValidPassword(String password) {
        boolean result = false;

        if (password == null || password.isEmpty()) {
            return false;
        }

        try {
            Pattern password_pattern = Pattern.compile(ApplicationConstants.PASSWORD_REGEX, Pattern.CASE_INSENSITIVE);
            Matcher matcher = password_pattern.matcher(password);
            result = matcher.find();
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
        return result;
    }
}
