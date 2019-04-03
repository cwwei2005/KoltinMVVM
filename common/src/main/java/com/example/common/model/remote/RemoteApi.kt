package com.example.common.model.remote

import com.example.common.model.entity.ArticleEntity
import com.example.common.model.entity.SelectEntity
import io.reactivex.Single
import retrofit2.http.GET


interface RemoteApi{
    @GET("article_list.php")
    fun getArticles(/*@Query("id") id: Int*/): Single<ArticleEntity>

    @GET("article_list.php")
    fun getSelect(/*@Query("id") id: Int*/): Single<SelectEntity>
}