package com.example.selectcar.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.example.common.BaseApplication
import com.example.common.model.MyRepository
import com.example.common.model.entity.SelectEntity
import com.example.common.model.remote.NetworkState

class SelectHomeViewModel : ViewModel(){

    private var entity: LiveData<out List<Any>>? = null

    fun getEntity(tClass:Class<out Any> = SelectEntity::class.java):LiveData<out List<Any>>?{
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