package com.rajdroid.roomdb

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao{
    @Insert
    fun addUser(user: User):Long

    @Query("SELECT * FROM User")
    fun getAllUsers():LiveData<List<User>>

    @Query("SELECT * FROM User where isActive = 1")
    fun getActiveUsers():List<User>


}