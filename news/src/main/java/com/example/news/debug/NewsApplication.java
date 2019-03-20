package com.example.news.debug;

import android.app.Application;
import android.util.Log;

public class NewsApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("tag", "debug NewsApplication");
    }
}
