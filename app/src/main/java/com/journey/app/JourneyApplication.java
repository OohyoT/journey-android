package com.journey.app;

import android.app.Application;

import com.journey.app.util.Pref;

public class JourneyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Pref.init(this);
    }

}
