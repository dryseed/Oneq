package com.example.oneq10.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.oneq10.db.dao.UserDao
import com.example.oneq10.db.entity.UserEntity

@Database(entities = [UserEntity::class], version = 2)
abstract class AppDatabase : RoomDatabase()  {
    abstract fun userDao(): UserDao
}