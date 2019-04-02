package com.example.news.debug;

import android.app.Application;
import android.util.Log;
import com.example.common.BaseApplication;

public class NewsApplication extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("tag", "debug NewsApplication");
    }
}
