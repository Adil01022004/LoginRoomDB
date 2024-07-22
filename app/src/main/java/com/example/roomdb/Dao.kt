package com.example.roomdb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface Dao {
    @Insert
    fun insertItem(item: Item)

    @Query("SELECT COUNT(*) FROM user_info WHERE email = :email AND password = :password")
    fun checkUser(email: String, password: String): Int
}
