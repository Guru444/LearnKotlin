package com.izmir.learnkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.orhanobut.hawk.Hawk
import kotlin.math.log

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        Hawk.init(this).build()

        var name = "Murat Karabulut"
        var isim = name.split(" ").toTypedArray()
        Log.i("Result nameSplit", isim.get(1))

        fibonacciCalculater()

        toplama()
        toplamWithParameter(5, 3)
        Log.i("Result returnFunction", toplamReturnFun(4,3).toString())
        varialable()
        dortIslem()
        stringProcess()
        logicProcess()
        loopProcess()
        geriyeListeDondur().forEach { Log.i("Result nameList", it) }

        var filterList: ArrayList<Int> = arrayListOf()
        filterList.add(1)
        filterList.add(1)
        filterList.add(1)
        filterList.add(1)
        filterList.filter { it > 5 }.forEach {
            Log.i("Result filterItemList", it.toString())
        }
        Log.i("Result findItem", filterList.find { it > 5 }.toString())
    }

    fun varialable(){
        var sayi = 1 // yeni değer atamaları mümkün
        val sayi2 = 1 // değeri değiştirilemez

        var number: Int = 0
        var name: String = "Ahmet"
        var floatNumber: Float = 22.45F
        var doubleNumber: Double = 22.44
        var charName: Char = 'a'
        var booleanVariable: Boolean = false
    }
    fun dortIslem(){
        var toplam = 5 + 4
        var cikarma = 5 - 1
        var carpma = 5 * 4
        var bolme = 6 / 3
    }
    fun stringProcess(){
        Log.i("Result","String işlemleri")
        val isim = "   Murat Karabulut   "
        Log.i("Result uppercase", isim.uppercase())
        Log.i("Result lowercase", isim.lowercase())
        Log.i("Result subString", isim.substring(3,6))
        Log.i("Result replace", isim.replace("Kara", "Beyaz"))
        Log.i("Result reversed", isim.reversed())
        Log.i("Result plus", isim.plus(" Derste"))
        Log.i("Result length", isim.length.toString())
        Log.i("Result trim", isim.trim())
        Log.i("Result get", isim.get(5).toString())
    }
    fun logicProcess(){
        Log.i("Result", "----- Logic İşlemleri ----")
        val havaDurumu: Boolean = false
        if (havaDurumu){
            Log.i("Result", "Dışarı çıkabilirsin")
        } else if(havaDurumu.not()){
            Log.i("Result", "Dışarı çıkamazsın")
        } else{
            Log.i("Result", "Kafana göre takıl")
        }
        val havaDurumuMetin = "yagisli"
        when(havaDurumuMetin){
            "gunesli"-> { Log.i("Result", "Dışarı çıkabilirsin") }
            "yagisli" -> { Log.i("Result", "Dışarı çıkamazsın") }
        }
    }
    fun loopProcess(){
        Log.i("Result", "----- Dizi İşlemleri ----")
        var dizi: ArrayList<String> = arrayListOf()
        dizi.add("Ahmet")
        dizi.add("Mehmet")
        dizi.add("yusuf")
        dizi.add("Can")
        dizi.add("Hatice")
        //0, 1, 1, 2, 3, 5, 8 ,13 , 21 ,34 ,55 ,
        /*dizi.add(5)
        dizi.add(12F)
        dizi.add(true)
        dizi.add(22.4)
        dizi.add('c')*/
        Log.i("Result", dizi.size.toString())
        Log.i("Result", dizi.last().toString())

        Log.i("Result", dizi.toString())
        for (i in 0..dizi.size){
            //Log.i("Result item", dizi.get(i))
        }
        dizi.forEach { item->
            Log.i("Result item2", item)
        }
    }
    fun toplama(){
        var toplam = 5 + 3
        Log.i("Result toplam", toplam.toString())
    }
    fun toplamWithParameter(sayi: Int, sayi2: Int){
        var toplam = sayi + sayi2
        Log.i("Result toplam", toplam.toString())
    }
    fun toplamReturnFun(sayi: Int, sayi2: Int) : Int{
        return sayi + sayi2
    }
    fun geriyeListeDondur() : ArrayList<String>{
        var liste: ArrayList<String> = arrayListOf()
        liste.add("Ahmet")
        liste.add("Ahmet2")
        liste.add("Ahmet3")
        return liste
    }
    fun fibonacciCalculater(){
        var listeFibo: ArrayList<Int> = arrayListOf()
        listeFibo.add(0)
        listeFibo.add(1)
        Log.i("Result fibonacci One", listeFibo.get(0).toString())
        Log.i("Result fibonacci Two", listeFibo.get(1).toString())
        for (i in 0..10){
            var newItem = listeFibo.get(i) + listeFibo.get(i + 1) // 0 -> 0, 1, 1, 2
            listeFibo.add(newItem)
            Log.i("Result fibonacci", listeFibo.get(i).toString())
        }
    }


}