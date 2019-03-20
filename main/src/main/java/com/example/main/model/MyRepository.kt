package com.example.main.model

import android.app.Activity
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.example.common.utils.LogUtils
import com.example.main.debug.MainApplication
import com.example.main.model.data.Article
import com.example.main.model.data.User
import com.example.main.model.remote.RemoteService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class MyRepository{

    private var mObservableProducts: MediatorLiveData<List<User>>? = null

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

    init {
        mObservableProducts = MediatorLiveData<List<User>>()
        mObservableProducts?.addSource(
            MyDataBase.getInstance(MainApplication.mainApp).userDao().loadAllData()
        ) { productEntities ->
            if (MyDataBase.getInstance(MainApplication.mainApp).userDao().loadAllData() != null) {
                mObservableProducts?.postValue(productEntities)
            }
        }
    }


    fun getArticle(ctx :Context): LiveData<Article>?{
        //todo 获取本地数据，没有则获取网络数据
        val list = MyDataBase.getInstance(ctx).articleDao().loadAllData().value
        val article = MyDataBase.getInstance(ctx).articleDao().loadData(0)
        LogUtils.e("tag","check db ${MyDataBase.checkDB(ctx)}")
        if (article.value == null){
            LogUtils.e("tag","get remote")
            val retrofit = Retrofit.Builder()
                .baseUrl("http://api.jcodecraeer.com/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(RemoteService::class.java)
            retrofit.getArticleDetail(/*8773*/)
                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ t: Article? ->
                    LogUtils.e("tag","Article:${t?.total_count}")
                    MyDataBase.getInstance(ctx).articleDao().save(t!!)
//                    article.value = t
                }, { t: Throwable? ->
                    LogUtils.e("tag","${t?.message.toString()}")
                })
        } else{
            LogUtils.e("tag","get local")
        }
        return article
    }



    fun getUser(ctx :Context): LiveData<List<User>>?{
        return mObservableProducts
//        val data = MyDataBase.getInstance(ctx).userDao().loadAllData()
//        val list = MyDataBase.getInstance(ctx).userDao().loadAllData().value
//        val user = MyDataBase.getInstance(ctx).userDao().loadData(0)
//        LogUtils.e("tag","check db ${MyDataBase.checkDB(ctx)}")
//        if (user.value == null){
//            LogUtils.e("tag","getUser remote")
//            val retrofit = Retrofit.Builder()
//                .baseUrl("http://api.jcodecraeer.com/")
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .addConverterFactory(GsonConverterFactory.create())
//                .build().create(RemoteService::class.java)
//            retrofit.getArticleDetail(/*8773*/)
//                .subscribeOn(Schedulers.io())
////                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe({ t: Article? ->
//                    LogUtils.e("tag","Article:${t?.total_count}")
//                    val user = User()
////                    user.id = 1
//                    user.name = "cww"
//                    user.password = "123456"
//                    MyDataBase.getInstance(ctx).userDao().save(user)
////                    article.value = t
//                }, { t: Throwable? ->
//                    LogUtils.e("tag","${t?.message.toString()}")
//                })
//        } else{
//            LogUtils.e("tag","getUser local")
//        }
//        return data
    }

}