package com.example.mycloset

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_favorites.*

var topList = arrayListOf<ClothesInfo>(
    ClothesInfo("1_1_0")
)
var bottomList = arrayListOf<ClothesInfo>(
    ClothesInfo("1_1_0")
)
var dressList = arrayListOf<ClothesInfo>(
    ClothesInfo("1_1_0")
)
var outwearList = arrayListOf<ClothesInfo>(
    ClothesInfo("1_1_0")
)

class FavoritesActivity : AppCompatActivity() {

    var _backButton: ImageView? = null

    fun reset() {
        all_favorites.isSelected = false
        top_favorites.isSelected = false
        bottom_favorites.isSelected = false
        dress_favorites.isSelected = false
        outwear_favorites.isSelected = false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorites)

        _backButton = findViewById(R.id.fav_back_button) as ImageView
        _backButton!!.setOnClickListener{
            finish()
        }
        /*
        all_favorites.setOnClickListener{
            reset()
            //var allList = topList + bottomList + dressList + outwearList
            //val adapter = ContactsListAdapter(this, allList)
            //ClothesRecyclerview.adapter = adapter
            all_favorites.isSelected = true
        }
        top_favorites.setOnClickListener{
            reset()
            val adapter = ContactsListAdapter(this, topList)
            ClothesRecyclerview.adapter = adapter
            top_favorites.isSelected = true
        }
        bottom_favorites.setOnClickListener{
            reset()
            val adapter = ContactsListAdapter(this, bottomList)
            ClothesRecyclerview.adapter = adapter
            bottom_favorites.isSelected = true
        }
        dress_favorites.setOnClickListener{
            reset()
            val adapter = ContactsListAdapter(this, dressList)
            ClothesRecyclerview.adapter = adapter
            dress_favorites.isSelected = true
        }
        outwear_favorites.setOnClickListener{
            reset()
            val adapter = ContactsListAdapter(this, outwearList)
            ClothesRecyclerview.adapter = adapter
            outwear_favorites.isSelected = true
        }
        */
        val lay = LinearLayoutManager(this)
        ClothesRecyclerview.layoutManager = lay
        ClothesRecyclerview.setHasFixedSize(true)
    }
}

