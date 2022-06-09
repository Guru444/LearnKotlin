package com.izmir.learnkotlin.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.izmir.learnkotlin.R
import com.izmir.learnkotlin.adapter.MessageListAdapter
import com.izmir.learnkotlin.databinding.ActivityMessageListBinding
import com.izmir.learnkotlin.model.MessageItem

class MessageListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMessageListBinding
    private var messageListAdapter = MessageListAdapter()
    private var messageList: ArrayList<MessageItem> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMessageListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        messageList.add(MessageItem("Yunus","Neredesin sen",false,"13:34"))
        messageList.add(MessageItem("Enes","Nerede kaldın",true,"12.44"))
        messageList.add(MessageItem("Adna","Hayde",true,"13:34"))
        messageList.add(MessageItem("Onur erdem","Geldin mi",true,"13:34"))
        messageList.add(MessageItem("Emre","Ekmek al",false,"13:34"))

        binding.apply {
            rvMessagelist.layoutManager = LinearLayoutManager(this@MessageListActivity, LinearLayoutManager.VERTICAL, false)
            rvMessagelist.adapter = messageListAdapter

            messageListAdapter.listeyiDoldur(messageList)

            messageListAdapter.messageItemClickListener = {
                Log.i("Item", it)
            }
        }

    }
}