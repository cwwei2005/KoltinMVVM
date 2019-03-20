package com.example.main.debug

import android.app.Application
import android.content.Context
import android.util.Log
import com.facebook.stetho.Stetho

class MainApplication : Application() {

    companion object {
        lateinit var mainApp:Context
        var isDebug = true
    }

    override fun onCreate() {
        super.onCreate()
        mainApp = this
        Log.e("tag", "debug MainApplication")
        if (isDebug){
            Stetho.initializeWithDefaults(this)
        }
    }
}
