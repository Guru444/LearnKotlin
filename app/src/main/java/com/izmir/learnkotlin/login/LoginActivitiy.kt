package com.izmir.learnkotlin.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.material.tabs.TabLayout
import com.izmir.learnkotlin.databinding.ActivityLoginBinding
import com.izmir.learnkotlin.model.GameList
import com.izmir.learnkotlin.util.Constant
import com.izmir.learnkotlin.util.list
import com.izmir.learnkotlin.util.listCount
import com.izmir.learnkotlin.util.showImage
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jsoup.Jsoup
import org.jsoup.nodes.Document


class LoginActivitiy  : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private var gameNameList: ArrayList<String> = arrayListOf()
    private var gameIdList: ArrayList<String> = arrayListOf()
    private var gameList: ArrayList<GameList> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            GlobalScope.launch {
                val doc: Document = Jsoup.connect(Constant.BASE_URL + Constant.EXT_URL).get()
                for (div in doc.select(Constant.QUERY_REGEX_GAME_NAME)) {
                    gameNameList.add(div.text())
                }
                for (div in doc.select(Constant.QUERY_REGEX_GAME_ID)) {
                    gameIdList.add(div.attributes().html().split("=").get(2).replace("\"",""))
                }
                if (gameNameList.isNotEmpty()){
                    gameNameList.let {
                        for (x in 0 until gameNameList.size - 1){
                            gameList.add(GameList(it.getOrNull(x), gameIdList.get(x).toInt()))
                        }
                    }
                }


                gameList.forEachIndexed { index, gameList ->
                    runOnUiThread {
                        tblGame.addTab(tblGame.newTab().setText(gameList.gameName).setId(gameList.gameId ?: 0))
                    }
                    Log.i("Result", gameList.gameName.toString() + gameList.gameId.toString())
                }

            }


            tblGame.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
                override fun onTabSelected(tab: TabLayout.Tab?) {
                   //Log.i("TabSelect", gameNameList.get(tab?.position ?: 0) +" - "+ tab?.id.toString())
                    GlobalScope.launch {
                        val doc: Document = Jsoup.connect(Constant.URL_GAME_DETAIL + tab?.id.toString()).get()
                        runOnUiThread {
                            Log.i("TabSelect",doc.select(".tab-pane img").outerHtml().split("=").get(1).replace("\"","").replace("?t","") )
                            var imageUrl = doc.select(".tab-pane img").outerHtml().split("=").get(1).replace("\"","").replace("?t","").replace(">","")
                            this@LoginActivitiy.showImage(imageUrl, binding.ivGameImg)
                        }
                    }
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {

                }

                override fun onTabReselected(tab: TabLayout.Tab?) {

                }

            })


            btnRegister.setOnClickListener {
                if (formValidation()){
                    val intent = Intent(this@LoginActivitiy, WelcomeActiivity::class.java)
                    with(intent){
                        putExtra(Constant.USER_NAME_KEY ,edtName.text.toString())
                        putExtra(Constant.USER_SURNAME_KEY, edtSurname.text.toString())
                    }
                }
                val intentMessage = Intent(this@LoginActivitiy, MessageListActivity::class.java)
                startActivity(intentMessage)
            }
        }
    }
    private fun formValidation() : Boolean{
        var isValid= true
        if (binding.edtName.text.isEmpty()){
            isValid = false
            Log.i("ErrorValid", "Adı alanı boş")
        }
        if (binding.edtSurname.text.isEmpty()){
            isValid = false
            Log.i("ErrorValid", "Soyad alanı boş")
        }
       return isValid
    }

}