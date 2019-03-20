package com.example.main.model.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.main.model.data.User

@Dao
interface UserDao{
    @Query("SELECT * FROM user")
    abstract fun loadAllData(): LiveData<List<User>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertAll(list: List<User>)

    @Query("select * from user where id = :id")
    abstract fun loadData(id: Int): LiveData<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(article: User)

    @Query("select * from user where id = :id")
    abstract fun loadDataSync(id: Int): User

    //注意：Query()的参数使用SQL语句报错，参考https://www.jianshu.com/p/61e7a92d3ba1
    @Query("select * from user where id like '%' || :query || '%'")
    abstract fun searchAllData(query: String?): LiveData<List<User>>
}