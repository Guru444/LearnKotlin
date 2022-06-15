package com.izmir.learnkotlin.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.izmir.learnkotlin.R
import com.izmir.learnkotlin.model.MessageItem
import kotlinx.android.synthetic.main.activity_learn_layout_constraint.view.*

class MessageListAdapter : RecyclerView.Adapter<MessageListAdapter.MessageVieHolder>() {

    private var messageList: ArrayList<MessageItem> = arrayListOf()
    var messageItemClickListener: (String) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MessageVieHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.activity_learn_layout_constraint, parent, false)
        )

    override fun onBindViewHolder(holder: MessageListAdapter.MessageVieHolder, position: Int) {
       holder.bind(messageList[position]) // ahmet, mehmet, yusuf -> 0
    }

    override fun getItemCount() = messageList.size

    inner class MessageVieHolder(view: View) :  RecyclerView.ViewHolder(view){
        fun bind(messageItem: MessageItem) {
            itemView.tv_user_name.text = messageItem.userName
            itemView.tv_last_message.text = messageItem.userLastMessage
            itemView.tv_message_time.text = messageItem.messageTime
            if (messageItem.isRead){
                itemView.tv_user_name.setTextColor(ContextCompat.getColor(itemView.context,
                    R.color.teal_700
                ))
            }

            //Long press
            itemView.tv_user_name.setOnLongClickListener {
               //messageItemClickListener(messageItem.userName)
                return@setOnLongClickListener false
            }
            itemView.rootView.setOnClickListener {
                messageItemClickListener(messageItem.userName)
            }
            itemView.remove_item.setOnClickListener {
                messageList.removeAt(adapterPosition)
                notifyItemRemoved(adapterPosition)
            }
        }
    }

    fun listeyiDoldur(messageListesi: ArrayList<MessageItem>){
        messageList.clear()
        messageList.addAll(messageListesi)
        notifyDataSetChanged()
    }

}