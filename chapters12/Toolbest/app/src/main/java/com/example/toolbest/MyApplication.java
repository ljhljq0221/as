package com.example.toolbest;

import android.app.Application;
import android.content.Context;
import android.provider.ContactsContract;

public class MyApplication extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getContext(){
        return context;
    }
}
