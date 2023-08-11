package com.example.spaceowner;

import android.app.Application;
import android.content.Context;

public class OwnerApplication extends Application {
    private static OwnerApplication instance;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static OwnerApplication getInstance(){
        return instance;
    }
    public static Context getContext(){
        return instance.getApplicationContext();
    }
}
