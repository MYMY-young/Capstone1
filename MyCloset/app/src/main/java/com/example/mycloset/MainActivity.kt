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
import java.io.FileOutputStream

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


//test
        var downloadManager : DownloadManager =  getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        var downloadId : Long = -1L
        val file = File(getExternalFilesDir(null), "RmuL-BPFi2Q563.jpg")
        val Url = "http://54.180.134.56/media/exp4/ll/0+skirt/RmuL-BPFi2Q100.jpg"
        val request = DownloadManager.Request(Uri.parse(Url))
            .setTitle("RmuL-BPFi2Q100.jpg")
            .setDescription("RmuL-BPFi2Q100.jpg")
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE)
            .setDestinationUri(Uri.fromFile(file))
            .setAllowedOverMetered(true)
            .setAllowedOverRoaming(true)
            .addRequestHeader("authorization", Common.returnCookie())

        downloadId = downloadManager.enqueue(request)


        while(true){
            if(getStatus(downloadId, downloadManager) == "Successful")break
            Log.d("Download",getStatus(downloadId, downloadManager))

        }

                Log.d("DownloadHTTP", "path : " + file.path)
    }
    private fun getStatus(id: Long, downloadManager: DownloadManager): String {
        val query: DownloadManager.Query = DownloadManager.Query()
        query.setFilterById(id)
        var cursor = downloadManager.query(query)
        if (!cursor.moveToFirst()) {
            Log.e("TAG", "Empty row")
            return "Wrong downloadId"
        }

        var columnIndex = cursor.getColumnIndex(DownloadManager.COLUMN_STATUS)
        var status = cursor.getInt(columnIndex)
        var columnReason = cursor.getColumnIndex(DownloadManager.COLUMN_REASON)
        var reason = cursor.getInt(columnReason)
        var statusText: String

        when (status) {
            DownloadManager.STATUS_SUCCESSFUL -> statusText = "Successful"
            DownloadManager.STATUS_FAILED -> {
                statusText = "Failed: $reason"
            }
            DownloadManager.STATUS_PENDING -> statusText = "Pending"
            DownloadManager.STATUS_RUNNING -> statusText = "Running"
            DownloadManager.STATUS_PAUSED -> {
                statusText = "Paused: $reason"
            }
            else -> statusText = "Unknown"
        }

        return statusText
    }
}