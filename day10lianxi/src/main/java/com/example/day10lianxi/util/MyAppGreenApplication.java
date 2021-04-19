package com.example.day10lianxi.util;

import android.app.Application;

public class MyAppGreenApplication extends Application {
    private static MyAppGreenApplication appGreenApplication;

    public static MyAppGreenApplication getAppGreenApplication() {
        return appGreenApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appGreenApplication = this;
    }
}
