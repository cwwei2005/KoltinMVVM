package com.example.main.vm

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.example.common.BaseApplication
//import com.example.main.MainApplication
//import com.example.main.debug.MainApplication
import com.example.main.model.MyRepository
import com.example.main.model.entity.ArticleEntity
import com.example.main.model.remote.NetworkState

class HomeViewModel : ViewModel(){

    private var entity: LiveData<out List<Any>>? = null

    fun getEntity(tClass:Class<out Any> = ArticleEntity::class.java):LiveData<out List<Any>>?{
        entity = MyRepository.getInstance().getEntity(BaseApplication.getInstance(), tClass)
        return entity
    }

    fun getNetworkState():LiveData<NetworkState>?{
        return MyRepository.getInstance().getNetworkState(BaseApplication.getInstance())
    }

    fun refresh(tClass: Class<out Any>){
        MyRepository.getInstance().refresh(BaseApplication.getInstance(), tClass, entity as MediatorLiveData<List<Any>>?)
    }

    fun getNextPage(tClass: Class<out Any>, insert:Boolean){
        MyRepository.getInstance().getNextPage(BaseApplication.getInstance(), tClass, insert)
    }
}