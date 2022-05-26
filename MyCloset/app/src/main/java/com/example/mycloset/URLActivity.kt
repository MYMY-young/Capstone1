package com.example.mycloset

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView

class URLActivity : YouTubeBaseActivity() {
    var _backButton: ImageView? = null
    var _showButton: Button? = null
    var _nextButton: Button? = null
    var _addressText: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_url)

        _backButton = findViewById(R.id.url_back_button) as ImageView
        _showButton = findViewById(R.id.show_button) as Button
        _nextButton = findViewById(R.id.next_button) as Button
        _addressText = findViewById(R.id.Address_input) as EditText

        _backButton!!.setOnClickListener { finish() }
        _showButton!!.setOnClickListener {
            val videoId = _addressText!!.text.toString().substring(_addressText!!.text.toString().lastIndexOf("/") + 1)
            showVideo(videoId)
            _showButton!!.isEnabled = true
        }
        _nextButton!!.setOnClickListener {
            startActivity(Intent(this, ClothesActivity::class.java))
        }
    }

    fun showVideo(videoId: String) {
        val youtubeView = findViewById<YouTubePlayerView>(R.id.youtubeView)
        youtubeView.initialize("develop", object : YouTubePlayer.OnInitializedListener {
            override fun onInitializationSuccess(
                provider: YouTubePlayer.Provider,
                player: YouTubePlayer,
                wasRestored: Boolean
            ) {
                if (!wasRestored) {
                    player.cueVideo(videoId)
                }
            }
            override fun onInitializationFailure(
                provider: YouTubePlayer.Provider?,
                result: YouTubeInitializationResult?
            ) { }
        })
    }
}