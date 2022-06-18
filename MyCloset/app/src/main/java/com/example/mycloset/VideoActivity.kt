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
import com.google.android.youtube.player.internal.c
import kotlinx.android.synthetic.main.activity_video.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.BufferedReader
import java.io.File
import java.io.FileReader

var videoList = ArrayList<VideoInfo>()

class VideoActivity : AppCompatActivity() {




    val TAG = "VideoActivity"
    var _backButton: ImageView? = null

   public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)





        // downloadService(i, i.substring(i.lastIndexOf("/") + 1), downloadId)
        // txt파일 열어서 videoList 초기화 시키기
        // videoList.add(VideoInfo("path","url"))
        val path = "$filesDir/mycloset_mp4.txt"

///storage/self/primary/Android/data/com.example.mycloset/files/person_01
// +long_sleeved_outwear.mp4+
// http.video.com

        if(!File(path).exists()) return
        File(path).forEachLine {
            var temp = it
            var chk = true
            var _size = videoList.size
            Log.d(TAG, "Size : ${_size}")

            val hint = temp.split("+")
            if(!File("${hint[0]}+${hint[1]}").exists()){
                Log.d(TAG, "Boolean : ${File("${hint[0]}+${hint[1]}").exists()}")
                chk = false
            }

            var videoName = "${hint[0].substring(hint[0].lastIndexOf("/")+1)}+${hint[1]}"
           if(_size > 0) {

                for (temp in videoList) {
                    if (temp.videoname == videoName || !chk) {
                        Log.d(TAG, "Boolean : ${File("${hint[0]}+${hint[1]}").exists()}")
                        chk = false
                        break
                    }

                }
            }
            if(chk){
                videoList.add(VideoInfo(temp))
            }


            Log.d(TAG, "LIST: ${videoList.toString()}")
        }




        val adapter = VideoListAdapter(this, videoList)
        VideoRecyclerview.adapter = adapter

        val lay = LinearLayoutManager(this)
        VideoRecyclerview.layoutManager = lay
        VideoRecyclerview.setHasFixedSize(true)



        _backButton = findViewById<ImageView>(R.id.video_back_button)
        _backButton!!.setOnClickListener{
            finish()
        }




    }




}

