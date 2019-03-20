package com.example.main.model.remote

import com.example.main.model.data.Article
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET


interface RemoteService{
    @GET("article_list.php")
    fun getArticleDetail(/*@Query("id") id: Int*/): Single<Article>
}