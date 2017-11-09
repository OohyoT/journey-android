package com.journey.app.util;

import android.content.Context;
import android.content.SharedPreferences;

public class Pref {

    private final static String PREF_KEY = "com.journey.app.pref";

    private static SharedPreferences pref;

    public static void init(Context context) {
        pref = context.getSharedPreferences(PREF_KEY, Context.MODE_PRIVATE);
    }

    /**
     * Returns an integer value from preferences.
     *
     * @param key key of the integer value
     * @return the integer value read, or 0 if the value cannot be read
     */
    public static int getInteger(String key) {
        return pref.getInt(key, 0);
    }

    /**
     * Returns an integer value from preferences.
     *
     * @param key          key of the integer value
     * @param defaultValue default value if the value cannot be read
     * @return the integer value read, or {@code defaultValue} if it cannot be read
     */
    public static int getInteger(String key, int defaultValue) {
        return pref.getInt(key, defaultValue);
    }

    /**
     * Returns a boolean value from preferences.
     *
     * @param key key of the boolean value
     * @return the boolean value read, or {@code false} if it cannot be read
     */
    public static boolean getBoolean(String key) {
        return pref.getBoolean(key, false);
    }

    /**
     * Returns a boolean value from preferences.
     *
     * @param key          key of the boolean value
     * @param defaultValue default value if the value cannot be read
     * @return the boolean value read, or {@code defaultValue} if it cannot be read
     */
    public static boolean getBoolean(String key, boolean defaultValue) {
        return pref.getBoolean(key, defaultValue);
    }

    /**
     * Returns a string value from preferences.
     *
     * @param key key of the string value
     * @return the string value read, or an empty string if it cannot be read
     */
    public static String getString(String key) {
        return pref.getString(key, "");
    }

    /**
     * Returns a float value from preferences.
     *
     * @param key key of the float value
     * @return the float value read, or 0f if it cannot be read
     */
    public static float getFloat(String key) {
        return pref.getFloat(key, 0F);
    }

    /**
     * Returns a long value from preferences.
     *
     * @param key key of the long value
     * @return the long value read, or 0L if it cannot be read
     */
    public static long getLong(String key) {
        return pref.getLong(key, 0L);
    }

    /**
     * Sets an integer value into preferences.
     *
     * @param key   key of the preference
     * @param value value of the preference
     */
    public static void set(String key, int value) {
        pref.edit().putInt(key, value).apply();
    }

    /**
     * Sets a boolean value into preferences.
     *
     * @param key   key of the preference
     * @param value value of the preference
     */
    public static void set(String key, boolean value) {
        pref.edit().putBoolean(key, value).apply();
    }

    /**
     * Sets a string value into preferences.
     *
     * @param key   key of the preference
     * @param value value of the preference
     */
    public static void set(String key, String value) {
        pref.edit().putString(key, value).apply();
    }

    /**
     * Sets a float value into preferences.
     *
     * @param key   key of the preference
     * @param value value of the preference
     */
    public static void set(String key, float value) {
        pref.edit().putFloat(key, value).apply();
    }

    /**
     * Sets a long value into preferences.
     *
     * @param key   key of the preference
     * @param value value of the preference
     */
    public static void set(String key, long value) {
        pref.edit().putLong(key, value).apply();
    }

}
