package com.example.andranikh.lab2;

import android.app.Application;

/**
 * Created by andranikh on 5/6/17.
 */

public class App extends Application{

    private static App instance;

    public static App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
