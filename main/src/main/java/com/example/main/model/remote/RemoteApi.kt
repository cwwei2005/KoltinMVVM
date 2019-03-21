package com.example.main.model.remote

import com.example.main.model.entity.ArticleEntity
import io.reactivex.Single
import retrofit2.http.GET


interface RemoteApi{
    @GET("article_list.php")
    fun getArticles(/*@Query("id") id: Int*/): Single<ArticleEntity>
}