package com.example.oneq10.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.oneq10.db.entity.UserEntity

@Dao
interface UserDao {
    @Insert
    fun insert(user: UserEntity)

    @Query("SELECT * FROM USER WHERE USERID =  :userId")
    fun getUser(userId: String): UserEntity

}