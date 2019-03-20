package com.example.common

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity(private val layoutId:Int?) : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (layoutId != null) setContentView(layoutId)
        activityInit()
    }

    open fun activityInit(){

    }
}