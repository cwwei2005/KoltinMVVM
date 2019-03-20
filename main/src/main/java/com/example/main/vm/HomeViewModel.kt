package com.example.main.vm

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.main.debug.MainApplication
import com.example.main.model.MyRepository
import com.example.main.model.data.Article
import com.example.main.model.data.User

class HomeViewModel : ViewModel(){

    private var user:LiveData<User>? = null
    private var mObservableProducts: MediatorLiveData<List<User>>? = null

    init {
        mObservableProducts = MediatorLiveData<List<User>>()
        mObservableProducts?.setValue(null)
        val products = MyRepository.getInstance().getUser(MainApplication.mainApp)
        // observe the changes of the products from the database and forward them
        mObservableProducts?.addSource<List<User>>(products!!, { mObservableProducts!!.setValue(it) })
    }

    fun getArticle(ctx:Context): LiveData<Article>?{
        return MyRepository.getInstance().getArticle(ctx)
    }

    fun getUser(ctx:Context): LiveData<List<User>>?{
        return mObservableProducts
    }
}