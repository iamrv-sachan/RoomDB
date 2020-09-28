package com.rajdroid.roomdb

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
@Entity
data class User(
    val name:String,
val age:String,
val isActive:Boolean,
@PrimaryKey(autoGenerate = true)
val id:Long=0L


)