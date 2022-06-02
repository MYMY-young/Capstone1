package com.example.mycloset

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var _settingButton: ImageView? = null
    var _urlButton: Button? = null
    var _favButton: Button? = null
    var _videoButton: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        _settingButton = findViewById(R.id.setting_button) as ImageView
        _urlButton = findViewById(R.id.url_button) as Button
        _favButton = findViewById(R.id.fav_button) as Button
        _videoButton = findViewById(R.id.video_button) as Button

        _settingButton!!.setOnClickListener {
            startActivity(Intent(this, InfoActivity::class.java))
            _settingButton!!.isEnabled = true
        }

        _urlButton!!.setOnClickListener {
            startActivity(Intent(this, URLActivity::class.java))
            _urlButton!!.isEnabled = true
        }

        _favButton!!.setOnClickListener {
            startActivity(Intent(this, FavoritesActivity::class.java))
            _favButton!!.isEnabled = true
        }

        _videoButton!!.setOnClickListener {
            startActivity(Intent(this, VideoActivity::class.java))
            _videoButton!!.isEnabled = true
        }
    }
}