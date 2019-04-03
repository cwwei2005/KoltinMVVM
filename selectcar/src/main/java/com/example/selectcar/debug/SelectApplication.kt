package com.example.selectcar.debug

import android.util.Log
import com.example.common.BaseApplication
//import com.example.main.model.remote.myModule
//import com.example.main.model.remote.appModule
import com.facebook.stetho.Stetho

class SelectApplication : BaseApplication() {

    companion object {
//        lateinit var mainApp:Context
        var isDebug = true
    }

    override fun onCreate() {
        super.onCreate()
        Log.e("tag", "debug SelectApplication")
//        mainApp = this
//        startKoin(this, listOf(myModule))
        if (isDebug){
            Stetho.initializeWithDefaults(this)
        }
    }
}
