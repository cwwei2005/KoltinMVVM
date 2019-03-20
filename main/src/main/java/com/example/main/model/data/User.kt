package com.example.main.model.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
class User{
    @PrimaryKey(autoGenerate = true)
    var id = 1
    var name:String? = null
    var password:String? = null
}