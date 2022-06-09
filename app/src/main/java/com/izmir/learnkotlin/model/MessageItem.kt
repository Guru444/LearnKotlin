package com.izmir.learnkotlin.model

data class MessageItem(
    var userName: String,
    var userLastMessage: String,
    var isRead: Boolean,
    var messageTime: String
)
