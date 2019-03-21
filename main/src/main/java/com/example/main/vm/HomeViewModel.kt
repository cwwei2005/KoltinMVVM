package com.example.main.vm

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.main.model.MyRepository
import com.example.main.model.remote.NetworkState

class HomeViewModel : ViewModel(){

    fun getData(ctx :Context, tClass:Class<out Any>):LiveData<out List<Any>>?{
        return MyRepository.getInstance().getData(ctx, tClass)
    }

    fun getNetworkState(ctx :Context):LiveData<NetworkState>?{
        return MyRepository.getInstance().getNetworkState(ctx)
    }
}