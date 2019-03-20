package com.example.main.model.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.main.model.data.Article

@Dao
interface ArticleDao{
    @Query("SELECT * FROM Article")
    abstract fun loadAllData(): LiveData<List<Article>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertAll(list: List<Article>)

    @Query("select * from Article where id = :id")
    abstract fun loadData(id: Int): LiveData<Article>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(article: Article)

    @Query("select * from Article where id = :id")
    abstract fun loadDataSync(id: Int): Article

    //注意：Query()的参数使用SQL语句报错，参考https://www.jianshu.com/p/61e7a92d3ba1
    @Query("select * from Article where id like '%' || :query || '%'")
    abstract fun searchAllData(query: String?): LiveData<List<Article>>
}