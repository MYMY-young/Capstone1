package com.example.mycloset
import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button

import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_highlight.*
import java.io.File

var highList = arrayListOf<HighInfo>(
    HighInfo("1_1"),
    HighInfo("1_2")
)

class HighlightActivity : AppCompatActivity() {



    val TAG = "HighlightActivity"
    var _mainButton: ImageView? = null
    var _nextButton: Button? = null
    //var _clothesfile = File("clothes.txt")
    public var clothesList = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_highlight)



        _mainButton = findViewById(R.id.high_main_button) as ImageView
        _nextButton = findViewById(R.id.high_next_button) as Button

        _mainButton!!.setOnClickListener {
            _mainButton!!.isEnabled = true
            startActivity(Intent(this, MainActivity::class.java))
        }
        _nextButton!!.setOnClickListener {
            var clothes: String = ""
            val fileOutputStream = openFileOutput("myclothes.txt", Context.MODE_APPEND)
            //fileOutputStream.write(clothes.toByteArray()
            for(str in 0 .. clothesList.size - 1 step(1)) {
                //clothes = clothes.plus(clothesList.get(str) + '/')
                fileOutputStream.write(clothesList.get(str).toByteArray())
            }
            fileOutputStream.close()

            _nextButton!!.isEnabled = true
            startActivity(Intent(this, FavoritesActivity::class.java))
        }

        val adapter = HighListAdapter(this, highList, clothesList)
        HighRecyclerview.adapter = adapter
        val lay = LinearLayoutManager(this)
        HighRecyclerview.layoutManager = lay
        HighRecyclerview.setHasFixedSize(true)


    }



}


