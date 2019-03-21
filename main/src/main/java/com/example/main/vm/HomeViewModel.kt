package com.example.main.vm

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.main.model.MyRepository

class HomeViewModel : ViewModel(){

//    private var user:LiveData<UserEntity>? = null
//    private var mObservableProducts: MediatorLiveData<List<UserEntity>>? = MediatorLiveData<List<UserEntity>>()

//    init {
//        mObservableProducts = MediatorLiveData<List<UserEntity>>()
//        mObservableProducts?.setValue(null)
//        val products = MyRepository.getInstance().getUser(MainApplication.mainApp)
//        // observe the changes of the products from the database and forward them
//        mObservableProducts?.addSource<List<UserEntity>>(products!!, { mObservableProducts!!.setValue(it) })
//    }

//    fun getArticle(ctx:Context): LiveData<ArticleEntity>?{
//        return MyRepository.getInstance().getArticle(ctx)
//    }

//    fun getUser(ctx:Context): LiveData<List<UserEntity>>?{
//        return MyRepository.getInstance().getUser(ctx)
//    }

    fun getData(ctx :Context, tClass:Class<out Any>):LiveData<out List<Any>>?{
        return MyRepository.getInstance().getData(ctx, tClass)
    }
}