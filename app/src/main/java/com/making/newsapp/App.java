package com.making.newsapp;

import android.app.Application;
import android.content.Context;


public class App extends Application {
    public static App mInstance;
    public static Context mAppContext;




    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        this.setAppContext(getApplicationContext());
    }

    public static App getInstance() {
        return mInstance;
    }

    public static Context getAppContext() {

        return mAppContext;
    }

//    public static String getAppContext(Context mAppContext) {
//        ApplicationInfo applicationInfo = mAppContext.getApplicationInfo();
//        int stringId = applicationInfo.labelRes;
//        return stringId == 0 ? applicationInfo.nonLocalizedLabel.toString() : mAppContext.getString(stringId);
//    }

    public void setAppContext(Context mAppContext) {
        this.mAppContext = mAppContext;
    }


    public static boolean isActivityVisible() {
        return activityVisible;
    }

    public static void activityResumed() {
        activityVisible = true;
    }

    public static void activityPaused() {
        activityVisible = false;
    }

    private static boolean activityVisible;
}
