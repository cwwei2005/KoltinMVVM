package com.example.main.model

import android.app.Activity
import android.content.Context
import androidx.annotation.MainThread
import androidx.annotation.UiThread
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.example.common.utils.LogUtils
import com.example.main.model.entity.ArticleEntity
import com.example.main.model.local.MyDataBase
import com.example.main.model.remote.MyRetrofit
import com.example.main.model.remote.NetworkState
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers


class MyRepository{

    companion object {
        private var instance:MyRepository? = null
        fun getInstance():MyRepository{
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

    fun getData(ctx :Context, tClass:Class<out Any>):LiveData<out List<Any>>?{
        val localData = getLocalData(ctx, tClass)
        val data = MediatorLiveData<List<Any>>()
        data.addSource(localData) {list ->
            if (/*MyDataBase.mIsDatabaseCreated.value != null*/localData.value?.size!! > 0){
                data.postValue(list)  //异步通知更新
            } else {
                LogUtils.e("tag","get remote data...")
                getRemoteData(ctx, tClass){ ->
                    if (ctx is Activity){
                        val activity:Activity = ctx
                        activity.runOnUiThread {
                            data.addSource(getLocalData(ctx, tClass)){ list -> data.postValue(list) }
                        }
                    }
                }
            }
        }
        return data
    }


    private val networkState = MutableLiveData<NetworkState>()
    fun getNetworkState(ctx :Context):LiveData<NetworkState>?{
        networkState.value = NetworkState.IDEL
        return networkState
    }

    //获取数据库数据
    private fun getLocalData(ctx :Context, tClass:Class<out Any>) :LiveData<out List<Any>>{
        val dbData = when(tClass.newInstance()){
            is ArticleEntity -> (MyDataBase.getInstance(ctx).articleDao().loadAllData())
            else -> null
        }
        return dbData!!
    }

    //获取网络数据
    private fun getRemoteData(ctx :Context, tClass:Class<out Any>, update:()->Unit) {
        val single:Single<out Any>? = when(tClass.newInstance()){
            is ArticleEntity -> MyRetrofit.getInstance(ctx).remoteApi.getArticles()
            else -> null
        }
        single?.subscribeOn(Schedulers.io())!!  //io线程
        .subscribe({ t: Any? ->
            //获取成功，写入数据库
            when(tClass.newInstance()){  //io线程
                is ArticleEntity -> MyDataBase.getInstance(ctx).articleDao().insertAll(listOf(t) as List<ArticleEntity>)
            }
            LogUtils.e("tag","write done")
            update()
            networkState.postValue(NetworkState.LOADED)
//            update(ctx, tClass)
        }, { t: Throwable? ->
            LogUtils.e("tag","${t?.message.toString()}")
//            update()
            networkState.postValue(NetworkState.error(t?.message.toString()))
        })
    }


//    fun getArticle(ctx :Context): LiveData<List<ArticleEntity>>?{
//        return getData(ctx, ArticleEntity::class.java)
//    }



//    fun getUser(ctx :Context): LiveData<List<UserEntity>>?{
//        val data = MediatorLiveData<List<UserEntity>>()
//        val dbData = MyDataBase.getInstance(ctx).userDao().loadAllData()
//        data.addSource(dbData) { list ->
//            if (/*MyDataBase.mIsDatabaseCreated.value != null*/dbData.value?.size!! > 0){
//                data.postValue(list)  //调用postValue才会通知更新
//            } else {
//                LogUtils.e("tag","remote...")
//                MyRetrofit.getInstance(ctx).remoteApi.getArticles()
//                    .subscribeOn(Schedulers.io())  //io线程
////                .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe({ t: ArticleEntity? ->
//                        val user = UserEntity()
//                        user.name = "cww"
//                        user.password = "123456"
//                        MyDataBase.getInstance(ctx).userDao().save(user)  //线程
//                    }, { t: Throwable? ->
//                        LogUtils.e("tag","${t?.message.toString()}")
//                    })
//            }
//        }
//        return data
//    }


//    enum class Status {
//        LOADING,
//        SUCCESS,
//        FAILED
//    }
//
//    data class NetworkState private constructor(val status: Status, val msg: String? = null) {
//        companion object {
//            val LOADED = NetworkState(Status.SUCCESS)
//            val LOADING = NetworkState(Status.LOADING)
//            fun error(msg: String?) = NetworkState(Status.FAILED, msg)
//        }
//    }
//
//    data class Listing<T>(
//        val pageList: LiveData<out List<T>>,
//        val networkState: LiveData<NetworkState>,
//        val refreshState: LiveData<NetworkState>,
//        val refresh: () -> Unit,
//        val retry: () -> Unit
//    )

}