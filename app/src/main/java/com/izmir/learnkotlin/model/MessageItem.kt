package com.izmir.learnkotlin.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MessageItem(
    @ColumnInfo(name = "userName")
    var userName: String,
    @ColumnInfo(name = "userLastMessage")
    var userLastMessage: String,
    @ColumnInfo(name = "isRead")
    var isRead: Boolean,
    @ColumnInfo(name = "messageTime")
    var messageTime: String
){
    @PrimaryKey(autoGenerate = true) var uid: Int = 0
}

