package com.journey.app.util;

public class Auth {

    private final static String PREF_LOGINED = "LOGINED";
    private final static String PREF_LOGINED_USER_ID = "LOGINED_USER_ID";

    public static boolean hasLoggedIn() {
        return Pref.getBoolean(PREF_LOGINED);
    }

    public static void login(int userId) {
        Pref.set(PREF_LOGINED_USER_ID, userId);
        Pref.set(PREF_LOGINED, true);
    }

    public static void logout() {
        Pref.remove(PREF_LOGINED_USER_ID);
        Pref.set(PREF_LOGINED, false);
    }

    public static int getLoggedInUserId() {
        return Pref.getInteger(PREF_LOGINED_USER_ID);
    }

}
