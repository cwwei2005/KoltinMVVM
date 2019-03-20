package com.example.main

import android.content.Context
import android.util.Log
import com.example.common.BaseApplication

class MainApplication : BaseApplication() {

    companion object {
        var mainApp: Context? = null
    }

    override fun onCreate() {
        super.onCreate()
        mainApp = this
        Log.e("tag", "MainApplication")
    }
}
