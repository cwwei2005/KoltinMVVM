package com.example.main.model.remote

import android.content.Context
import android.util.Log
import com.example.common.utils.LogUtils
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

import java.io.IOException
import java.util.*
import java.util.concurrent.TimeUnit

/**只是将retrofit进行了一次封装，用单例模式进行初始化并获取api service实例。
 * Created by Administrator on 2017/12/2.
 */

class MyRetrofit private constructor(context: Context) {

    companion object {
        private var myRetrofit: MyRetrofit? = null
        fun getInstance(context: Context): MyRetrofit {
            if (myRetrofit == null) {
                synchronized(MyRetrofit::class.java) {
                    if (myRetrofit == null) {
                        myRetrofit = MyRetrofit(context)
                    }
                }
            }
            return myRetrofit!!
        }
    }

    val remoteApi: RemoteApi
    val cookies = HashSet<String>()
    var baseUrl = "http://api.jcodecraeer.com/"
        set(url) {
            field = url
            myRetrofit = null
        }

    init {
        //        OkHttpClient client = new OkHttpClient.Builder()
        //                .readTimeout(99, TimeUnit.SECONDS)
        //                .writeTimeout(99, TimeUnit.SECONDS)
        //                .connectTimeout(15, TimeUnit.SECONDS)
        //                .addInterceptor(new LogInterceptor())
        //                .cookieJar(new JavaNetCookieJar(new CookieManager(new PersistentCookieStore(MyApplication.getContext()), CookiePolicy.ACCEPT_ALL)))
        //                .build();
        val client = OkHttpClient.Builder()
            .addInterceptor(AddCookiesInterceptor())
            .addInterceptor(GetCookiesInterceptor())
            .connectTimeout(30, TimeUnit.SECONDS).build()
        val retrofit = Retrofit.Builder()
            .client(client)
//            .addConverterFactory(ScalarsConverterFactory.create())////增加返回值为String的支持
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(this.baseUrl)
            .build()
        remoteApi = retrofit.create(RemoteApi::class.java)
        LogUtils.e("tag", "baseUrl:"+this.baseUrl)
    }

    inner class GetCookiesInterceptor : Interceptor {
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
            val originalResponse = chain.proceed(chain.request())
            if (!originalResponse.headers("Set-Cookie").isEmpty()) {
                for (header in originalResponse.headers("Set-Cookie")) cookies.add(header)
            }
            return originalResponse
        }
    }

    inner class AddCookiesInterceptor : Interceptor {
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
            val builder = chain.request().newBuilder()
            for (cookie in cookies) {
                builder.addHeader("Cookie", cookie)
                Log.v("OkHttp", "Adding Header: $cookie") // This is done so I know which headers are being added; this interceptor is used after the normal logging of OkHttp
            }
            return chain.proceed(builder.build())
        }
    }
}
