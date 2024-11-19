package com.example.oneq10.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "USER")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "USERID")
    val userId: String,
    @ColumnInfo(name = "USERNAME")
    val userName: String,
    @ColumnInfo(name = "PASSWORD")
    val password: String
)
