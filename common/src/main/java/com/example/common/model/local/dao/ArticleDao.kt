package com.example.common.model.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.common.model.entity.ArticleEntity

@Dao
interface ArticleDao{
    @Query("SELECT * FROM ArticleEntity")
    abstract fun loadAllData(): LiveData<List<ArticleEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertAll(list: List<ArticleEntity>)

    @Query("select * from ArticleEntity where id = :id")
    abstract fun loadData(id: Int): LiveData<ArticleEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(articleEntity: ArticleEntity)

    @Query("select * from ArticleEntity where id = :id")
    abstract fun loadDataSync(id: Int): ArticleEntity

    //注意：Query()的参数使用SQL语句报错，参考https://www.jianshu.com/p/61e7a92d3ba1
    @Query("select * from ArticleEntity where id like '%' || :query || '%'")
    abstract fun searchAllData(query: String?): LiveData<List<ArticleEntity>>
}