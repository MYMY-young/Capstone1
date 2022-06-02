package com.example.mycloset

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.youtube.player.YouTubeBaseActivity
import kotlinx.android.synthetic.main.activity_video.*

var videoList = arrayListOf<VideoInfo>(
    VideoInfo("1_1"),
    VideoInfo("1_2")
)

class VideoActivity : YouTubeBaseActivity() {

    var _backButton: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)

        _backButton = findViewById(R.id.video_back_button) as ImageView
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

