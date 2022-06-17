package com.izmir.learnkotlin.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.izmir.learnkotlin.R
import com.izmir.learnkotlin.adapter.MessageListAdapter
import com.izmir.learnkotlin.databinding.ActivityMessageListBinding
import com.izmir.learnkotlin.model.MessageItem
import com.izmir.learnkotlin.room.MessageDao
import com.izmir.learnkotlin.room.MessageDatabase

class MessageListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMessageListBinding
    private var messageListAdapter = MessageListAdapter()
    private var messageList: ArrayList<MessageItem> = arrayListOf()
    private lateinit var messageDao: MessageDao
    private var messageLiveData: MutableLiveData<String> = MutableLiveData()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMessageListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = Room.databaseBuilder(applicationContext, MessageDatabase::class.java, "message").fallbackToDestructiveMigration().allowMainThreadQueries().build()
        messageDao = db.messageDao()


        /*messageList.add(MessageItem("Yunus","Neredesin sen",false,"13:34"))
        messageList.add(MessageItem("Enes","Nerede kaldın",true,"12.44"))
        messageList.add(MessageItem("Adna","Hayde",true,"13:34"))
        messageList.add(MessageItem("Onur erdem","Geldin mi",true,"13:34"))
        messageList.add(MessageItem("Emre","Ekmek al",false,"13:34"))*/

        binding.apply {
            rvMessagelist.layoutManager = LinearLayoutManager(this@MessageListActivity, LinearLayoutManager.VERTICAL, false)
            rvMessagelist.adapter = messageListAdapter

            messageListAdapter.listeyiDoldur(messageList)

            messageListAdapter.messageItemClickListener = {
                messageDao.deleteMessageItem(it)
            }

           messageDao.getAllMessage().observe(this@MessageListActivity,{
               it?.let { messageItem ->
                   messageList.clear()
                   messageItem.forEach {
                       messageList.add(it)
                   }
                   messageListAdapter.listeyiDoldur(messageList)
               }
           })

            btnAdded.setOnClickListener {
                //insert
                messageDao.saveMessage(MessageItem(userName = edtItem.text.toString(),userLastMessage = "XXX",isRead = false, messageTime = "12:45"))
                //messageList.add(MessageItem(edtItem.text.toString(),"XXX",false,"12:45"))
                //messageListAdapter.listeyiDoldur(messageList)
            }
            btnShowGame.setOnClickListener {
                var intent = Intent(this@MessageListActivity, GameListActivity::class.java)
                startActivity(intent)
            }
        }
    }
}