package com.izmir.learnkotlin.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import com.izmir.learnkotlin.R
import com.izmir.learnkotlin.adapter.GameListAdapter
import com.izmir.learnkotlin.databinding.ActivityGameListBinding
import com.izmir.learnkotlin.model.GameList
import com.izmir.learnkotlin.util.Constant
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

class GameListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGameListBinding
    private var gameListAdapter = GameListAdapter()
    private var gameNameList: ArrayList<String> = arrayListOf()
    private var gameIdList: ArrayList<String> = arrayListOf()
    private var gameImgList: ArrayList<String> = arrayListOf()
    private var gameList: ArrayList<GameList> = arrayListOf()
    private var liste: ArrayList<GameList> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            rvGameList.layoutManager = GridLayoutManager(this@GameListActivity,3)
            rvGameList.adapter = gameListAdapter


            GlobalScope.launch {
                val doc: Document = Jsoup.connect(Constant.BASE_URL + Constant.EXT_URL).get()
                for (div in doc.select(Constant.QUERY_REGEX_GAME_NAME)) {
                    gameNameList.add(div.text())
                }
                for (div in doc.select(Constant.QUERY_REGEX_GAME_ID)) {
                    gameIdList.add(div.attributes().html().split("=").get(2).replace("\"",""))
                }
                gameIdList.forEach {
                    val docImg: Document = Jsoup.connect(Constant.URL_GAME_DETAIL + it).get()
                    for (div in docImg.select(".tab-pane img")){
                        val imageUrl = div.outerHtml().split("=").get(1).replace("\"","").replace("?t","").replace(">","")
                        gameImgList.add(imageUrl)
                    }
                }

                if (gameNameList.isNotEmpty()){
                    gameNameList.let {
                        for (x in 0 until gameNameList.size - 1){
                            gameList.add(GameList(it.getOrNull(x), gameIdList.get(x).toInt(), gameImgList.get(x)))
                        }
                    }
                }

            }

            runOnUiThread {
                Handler(Looper.getMainLooper()).postDelayed({
                    if (gameList.isNotEmpty()){
                        gameListAdapter.gameList(gameList)
                    }
                },10000)
            }

           //liste.add(GameList("Deneme",1230,"https://i.hizliresim.com/dZaH2g.jpg"))
        }
    }
}