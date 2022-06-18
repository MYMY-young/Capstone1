package com.example.mycloset

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_favorites.*
import java.io.BufferedReader
import java.io.File
import java.io.FileReader

/*
var topList = arrayListOf<ClothesInfo>(
    ClothesInfo("1_1"),
    ClothesInfo("1_2")
)
var bottomList = arrayListOf<ClothesInfo>(
    ClothesInfo("2_1")
)
var dressList = arrayListOf<ClothesInfo>(
    ClothesInfo("3_1"),
    ClothesInfo("3_2")
)
var outwearList = arrayListOf<ClothesInfo>(
    ClothesInfo("4_1"),
    ClothesInfo("4_2")
)*/

class FavoritesActivity : AppCompatActivity() {

    var _backButton: ImageView? = null
    var allList = ArrayList<ClothesInfo>()
    var topList = ArrayList<ClothesInfo>()
    var bottomList = ArrayList<ClothesInfo>()
    var dressList = ArrayList<ClothesInfo>()
    var outwearList = ArrayList<ClothesInfo>()

    fun reset() {
        all_favorites.isSelected = false
        top_favorites.isSelected = false
        bottom_favorites.isSelected = false
        dress_favorites.isSelected = false
        outwear_favorites.isSelected = false
    }

    //892XUqlKPH0101.jpg+
    // Skirt+
    // https://youtu.be/892XUqlKPH0+
    // /storage/self/primary/Android/data/com.example.mycloset/files/892XUqlKPH0101.jpg

//
//    ClothesInfo(val image: String, val clothes: String, val url: String, val Videopath : String) {
//        var imagename: String = image
//        var clothesname: String = clothes
//        var urlname: String = url
//        var path : String = Videopath

        fun readFav() {
        if(!File("/data/data/com.example.mycloset/files/myclothes.txt").exists()) return
         File("/data/data/com.example.mycloset/files/myclothes.txt").forEachLine {
            var clothes = it.toString().split("+")
            allList.add(ClothesInfo(clothes[0], clothes[1], clothes[2],clothes[3]))
             if(clothes[1].contains("Top", true) || clothes[1].contains("Vest", true) || clothes[1].contains("Sling", true))
                 topList.add(ClothesInfo(clothes[0], clothes[1], clothes[2],clothes[3]))
             if(clothes[1].contains("Shorts", true) || clothes[1].contains("Trousers", true) || clothes[1].contains("Skirt", true))
                 bottomList.add(ClothesInfo(clothes[0], clothes[1], clothes[2],clothes[3]))
             if(clothes[1].contains("Dress", true))
                 dressList.add(ClothesInfo(clothes[0], clothes[1], clothes[2],clothes[3]))
             if(clothes[1].contains("Outwear", true))
                 outwearList.add(ClothesInfo(clothes[0], clothes[1], clothes[2],clothes[3]))

             Log.d("clothes", it.toString())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorites)
        readFav()

        _backButton = findViewById(R.id.fav_back_button) as ImageView
        _backButton!!.setOnClickListener{
            _backButton!!.isEnabled = true
            startActivity(Intent(this, MainActivity::class.java))
        }

        all_favorites.setOnClickListener{
            reset()
            //var allList = topList.plus(bottomList).plus(dressList).plus(outwearList)
            val adapter = ClothesListAdapter(this, allList)
            ClothesRecyclerview.adapter = adapter
            all_favorites.isSelected = true
        }
        top_favorites.setOnClickListener{
            reset()
            val adapter = ClothesListAdapter(this, topList)
            ClothesRecyclerview.adapter = adapter
            top_favorites.isSelected = true
        }
        bottom_favorites.setOnClickListener{
            reset()
            val adapter = ClothesListAdapter(this, bottomList)
            ClothesRecyclerview.adapter = adapter
            bottom_favorites.isSelected = true
        }
        dress_favorites.setOnClickListener{
            reset()
            val adapter = ClothesListAdapter(this, dressList)
            ClothesRecyclerview.adapter = adapter
            dress_favorites.isSelected = true
        }
        outwear_favorites.setOnClickListener{
            reset()
            val adapter = ClothesListAdapter(this, outwearList)
            ClothesRecyclerview.adapter = adapter
            outwear_favorites.isSelected = true
        }

        val lay = LinearLayoutManager(this)
        ClothesRecyclerview.layoutManager = lay
        ClothesRecyclerview.setHasFixedSize(true)
    }
}

