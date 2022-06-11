package com.example.mycloset

import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.youtube.player.YouTubeBaseActivity
import kotlinx.android.synthetic.main.activity_video.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File

var videoList = arrayListOf<VideoInfo>(
    VideoInfo("1_1"),
    VideoInfo("1_2")
)

class VideoActivity : YouTubeBaseActivity() {






    val TAG = "VideoActivity"
    var _backButton: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)

        _backButton = findViewById<ImageView>(R.id.video_back_button)
        _backButton!!.setOnClickListener{
            finish()
        }

        val adapter = VideoListAdapter(this, videoList)
        VideoRecyclerview.adapter = adapter

        val lay = LinearLayoutManager(this)
        VideoRecyclerview.layoutManager = lay
        VideoRecyclerview.setHasFixedSize(true)







    }




}

