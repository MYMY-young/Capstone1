package com.example.mycloset

import android.app.DownloadManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File

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



//        var downloadManager : DownloadManager =  getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
//        var downloadId : Long = -1L
//        val file = File(getExternalFilesDir(null), "892XUqlKPH0106.jpg")
//        val Url = "http://54.180.134.56/media/exp19/ll/0+skirt/892XUqlKPH0106.jpg"
//        val request = DownloadManager.Request(Uri.parse(Url))
//            .setTitle("892XUqlKPH0106.jpg")
//            .setDescription("892XUqlKPH0106.jpg")
//            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
//            .setDestinationUri(Uri.fromFile(file))
//            .setAllowedOverMetered(true)
//            .setAllowedOverRoaming(true)
//            .addRequestHeader("authorization", Common.returnCookie())
//
//
//        downloadId = downloadManager.enqueue(request)
//        Log.d("DownloadHTTP", "path : " + file.path)
    }
}