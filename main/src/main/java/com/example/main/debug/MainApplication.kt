package com.example.main.debug

import android.app.Application
import android.content.Context
import android.util.Log
//import com.example.main.model.remote.myModule
//import com.example.main.model.remote.appModule
import com.facebook.stetho.Stetho
import org.koin.android.BuildConfig
import org.koin.android.ext.android.startKoin
import org.koin.android.logger.AndroidLogger

class MainApplication : Application() {

    companion object {
        lateinit var mainApp:Context
        var isDebug = true
    }

    override fun onCreate() {
        super.onCreate()
        Log.e("tag", "debug MainApplication")
        mainApp = this
//        startKoin(this, listOf(myModule))
        if (isDebug){
            Stetho.initializeWithDefaults(this)
        }
    }
}
