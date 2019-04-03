package com.example.common.model.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.common.model.entity.SelectEntity

@Dao
interface SelectDao{
    @Query("SELECT * FROM SelectEntity")
    abstract fun loadAllData(): LiveData<List<SelectEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertAll(list: List<SelectEntity>)

    @Query("select * from SelectEntity where id = :id")
    abstract fun loadData(id: Int): LiveData<SelectEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(selectEntity: SelectEntity)

    @Query("select * from SelectEntity where id = :id")
    abstract fun loadDataSync(id: Int): SelectEntity

    //注意：Query()的参数使用SQL语句报错，参考https://www.jianshu.com/p/61e7a92d3ba1
    @Query("select * from SelectEntity where id like '%' || :query || '%'")
    abstract fun searchAllData(query: String?): LiveData<List<SelectEntity>>
}