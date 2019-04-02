package com.example.common;

import android.app.Application;
import android.util.Log;

public class BaseApplication extends Application {

//    public static BaseApplication getInstance(){
//        return BaseApplicationHolder.instance;
//    }
//
//    private static class BaseApplicationHolder{
//        private static final BaseApplication instance = new BaseApplication();
//    }

    private static BaseApplication instance;


    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        Log.e("tag","BaseApplication"+this.toString());
    }

    public static BaseApplication getInstance(){
        return instance;
    }
}
