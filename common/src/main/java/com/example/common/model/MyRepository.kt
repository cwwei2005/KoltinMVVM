package com.example.common.model

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.example.common.utils.LogUtils
import com.example.common.model.entity.ArticleEntity
import com.example.common.model.entity.SelectEntity
import com.example.common.model.local.MyDataBase
import com.example.common.model.remote.MyRetrofit
import com.example.common.model.remote.NetworkState
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import io.reactivex.android.schedulers.AndroidSchedulers

class MyRepository{

    companion object {
        private var instance: MyRepository? = null
        fun getInstance(): MyRepository {
            if (instance == null){
                synchronized(MyRepository::class.java){
                    if (instance == null){
                        instance = MyRepository()
                    }
                }
            }
            return instance!!
        }
    }

    fun getEntity(ctx :Context, tClass:Class<out Any>):LiveData<out List<Any>>?{
        val localData = getLocalData(ctx, tClass)
        val data = MediatorLiveData<List<Any>>()
        data.addSource(localData) {list ->
            if (/*MyDataBase.mIsDatabaseCreated.value != null*/localData.value?.size!! > 0){
                data.postValue(list)  //异步通知更新
            } else {
                LogUtils.e("tag","get remote data...")
                getRemoteData(ctx, tClass){ t ->  //io线程
                    when(tClass.newInstance()){
                        is ArticleEntity -> MyDataBase.getInstance(ctx).articleDao().insertAll(listOf(t) as List<ArticleEntity>)
                        is SelectEntity -> MyDataBase.getInstance(ctx).selectDao().insertAll(listOf(t) as List<SelectEntity>)
                    }
                    updateEntity(data, ctx, tClass)
                }
            }
        }
        return data
    }

    //通知数据更新
    private fun updateEntity(data:  MediatorLiveData<List<Any>>, ctx: Context, tClass:Class<out Any>){
        val obs = Observable.create(ObservableOnSubscribe<Any> { e -> e.onNext("哈哈") })  //创建被观察者并发送一个事件
        //订阅事件
        obs.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { t ->
                LogUtils.e("tag","$t")
                val localData = getLocalData(ctx, tClass)
                data.addSource(localData){ list ->
//                    data.postValue(list)
                    if (/*MyDataBase.mIsDatabaseCreated.value != null*/localData.value?.size!! > 0){
                        data.postValue(list)  //异步通知更新
                    }
                }
            }
    }



    private val networkState = MutableLiveData<NetworkState>()
    fun getNetworkState(ctx :Context):LiveData<NetworkState>?{
        networkState.value = NetworkState.IDEL
        return networkState
    }

    fun refresh(ctx :Context, tClass:Class<out Any>, entity: MediatorLiveData<List<Any>>?){
        getRemoteData(ctx, tClass){ t ->  //io线程
            when(tClass.newInstance()){
                is ArticleEntity -> MyDataBase.getInstance(ctx).articleDao().insertAll(listOf(t) as List<ArticleEntity>)
                is SelectEntity -> MyDataBase.getInstance(ctx).selectDao().insertAll(listOf(t) as List<SelectEntity>)
            }
            updateEntity(entity!!, ctx, tClass)
        }
    }

    fun getNextPage(ctx :Context, tClass:Class<out Any>, insert:Boolean){
        //
    }




    //获取数据库数据
    private fun getLocalData(ctx :Context, tClass:Class<out Any>) :LiveData<out List<Any>>{
        val dbData = when(tClass.newInstance()){
            is ArticleEntity -> (MyDataBase.getInstance(ctx).articleDao().loadAllData())
            is SelectEntity -> (MyDataBase.getInstance(ctx).selectDao().loadAllData())
            else -> null
        }
        return dbData!!
    }

    //获取网络数据
    private fun getRemoteData(ctx :Context, tClass:Class<out Any>, update:(t: Any?)->Unit) {
        val single:Single<out Any>? = when(tClass.newInstance()){
            is ArticleEntity -> MyRetrofit.getInstance(ctx).remoteApi.getArticles()
            is SelectEntity -> MyRetrofit.getInstance(ctx).remoteApi.getSelect()
            else -> null
        }
        single?.subscribeOn(Schedulers.io())!!  //io线程
        .subscribe({ t: Any? ->
            //获取成功，写入数据库
//            when(tClass.newInstance()){  //io线程
//                is ArticleEntity -> MyDataBase.getInstance(ctx).articleDao().insertAll(listOf(t) as List<ArticleEntity>)
//            }
            LogUtils.e("tag","write done")
            update(t)
            networkState.postValue(NetworkState.LOADED)
        }, { t: Throwable? ->
            LogUtils.e("tag","${t?.message.toString()}")
            networkState.postValue(NetworkState.error(t?.message.toString()))
        })
    }

}