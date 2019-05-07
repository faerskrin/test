package com.example.mediawithwear;

import android.app.Application;

public class App extends Application {

    public  static  DataManager dm;

    @Override
    public void onCreate() {
        super.onCreate();
        dm = new DataManager(getApplicationContext());
    }
}
