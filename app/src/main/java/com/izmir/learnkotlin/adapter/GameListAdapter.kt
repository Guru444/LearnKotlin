package com.izmir.learnkotlin.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.izmir.learnkotlin.R
import com.izmir.learnkotlin.model.GameList
import com.izmir.learnkotlin.util.showImgUrl
import kotlinx.android.synthetic.main.activity_learn_layout_constraint.view.*
import kotlinx.android.synthetic.main.activity_login.view.*
import kotlinx.android.synthetic.main.game_list_item_layout.view.*

class GameListAdapter : RecyclerView.Adapter<GameListAdapter.MessageVieHolder>() {

    private var gamelist: ArrayList<GameList> = arrayListOf()
    var messageItemClickListener: (String) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MessageVieHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.game_list_item_layout, parent, false)
        )

    override fun onBindViewHolder(holder: GameListAdapter.MessageVieHolder, position: Int) {
       holder.bind(gamelist[position])
    }

    override fun getItemCount() = gamelist.size

    inner class MessageVieHolder(view: View) :  RecyclerView.ViewHolder(view){
        fun bind(gameItem: GameList) {
            itemView.context.showImgUrl(gameItem.gameUrl, itemView.iv_game)
            itemView.tv_game_title.text = gameItem.gameName
        }
    }

    fun gameList(messageListesi: ArrayList<GameList>){
        gamelist.clear()
        gamelist.addAll(messageListesi)
        notifyDataSetChanged()
    }

}