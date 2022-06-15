package com.izmir.learnkotlin.util

import android.app.Activity
import android.content.Context
import android.util.Log
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.izmir.learnkotlin.model.GameList


fun Activity.showImage(url: String, imageView: ImageView){
    Glide
        .with(this)
        .load(url)
        .into(imageView)
}

fun Context.showImgUrl(url: String?, imageView: ImageView){
    Glide
        .with(this)
        .load(url)
        .into(imageView)
}



fun ArrayList<String>.listCount() : Int{
    return this.size
}

fun ArrayList<GameList>.list() : ArrayList<GameList>{
    return this.filter { it.gameName?.length!! > 20 } as ArrayList<GameList>
}
