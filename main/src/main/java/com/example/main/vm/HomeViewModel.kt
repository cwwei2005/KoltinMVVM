package com.example.main.vm

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.example.main.debug.MainApplication
import com.example.main.model.MyRepository
import com.example.main.model.entity.ArticleEntity
import com.example.main.model.remote.NetworkState

class HomeViewModel : ViewModel(){

    private var entity: LiveData<out List<Any>>? = null

    fun getEntity(/*ctx :Context= MainApplication.mainApp,*/ tClass:Class<out Any> = ArticleEntity::class.java):LiveData<out List<Any>>?{
        entity = MyRepository.getInstance().getEntity(MainApplication.mainApp, tClass)
        return entity
    }

    fun getNetworkState(/*ctx :Context=MainApplication.mainApp*/):LiveData<NetworkState>?{
        return MyRepository.getInstance().getNetworkState(MainApplication.mainApp)
    }

    fun refresh(tClass: Class<out Any>){
        MyRepository.getInstance().refresh(MainApplication.mainApp, tClass, entity as MediatorLiveData<List<Any>>?)
    }

    fun getNextPage(tClass: Class<out Any>, insert:Boolean){
        MyRepository.getInstance().getNextPage(MainApplication.mainApp, tClass, insert)
    }
}