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
import java.io.File

var videoList = arrayListOf<VideoInfo>(
    VideoInfo("1_1"),
    VideoInfo("1_2")
)

class VideoActivity : YouTubeBaseActivity() {


    //android downloader
    var downloadManager =  getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
    var downloadId : Long = -1L
    //


    val TAG = "VideoActivity"
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


//        다운로드 취소버튼.setOnClickListener{
//            if (downloadId != -1L) {
//                downloadManager.remove(downloadId)
//            }
//        }



        val intentFilter = IntentFilter()
        intentFilter.addAction(DownloadManager.ACTION_DOWNLOAD_COMPLETE)
        intentFilter.addAction(DownloadManager.ACTION_NOTIFICATION_CLICKED)
        registerReceiver(onDownloadComplete, intentFilter)

        downloadHTTP(baseContext, "hello", "hello", downloadManager )

    }


    private fun downloadHTTP(context: Context, filePath : String, fileName : String, downloadManager: DownloadManager) {
        val file = File(getExternalFilesDir(null), fileName)
        val fileurl = "http://3.38.212.229/${filePath}${fileName}"

        val request = DownloadManager.Request(Uri.parse(fileurl))
            .setTitle(fileName)
            .setDescription("Downloading...")
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            .setDestinationUri(Uri.fromFile(file))
            .setAllowedOverMetered(true)
            .setAllowedOverRoaming(true)

        request.addRequestHeader("cookie", Common.returnCookie())
        downloadId = downloadManager.enqueue(request)
        Log.d("DownloadHTTP", "path : " + file.path)

        val status = getStatus(downloadId, downloadManager)
        Log.d(TAG, status)
        Toast.makeText(this, status, Toast.LENGTH_SHORT).show()
    }




    private fun getStatus(id: Long, downloadManager: DownloadManager): String {
        val query: DownloadManager.Query = DownloadManager.Query()
        query.setFilterById(id)
        var cursor = downloadManager.query(query)
        if (!cursor.moveToFirst()) {
            Log.e(TAG, "Empty row")
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
            DownloadManager.STATUS_PAUSED-> {
                statusText = "Paused: $reason"
            }
            else -> statusText = "Unknown"
        }



        return statusText
    }

    private val onDownloadComplete = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1)
            if (DownloadManager.ACTION_DOWNLOAD_COMPLETE.equals(intent.action)) {
                if (downloadId == id) {
                    val query: DownloadManager.Query = DownloadManager.Query()
                    query.setFilterById(id)
                    var cursor = downloadManager.query(query)
                    if (!cursor.moveToFirst()) {
                        return
                    }

                    var columnIndex = cursor.getColumnIndex(DownloadManager.COLUMN_STATUS)
                    var status = cursor.getInt(columnIndex)
                    if (status == DownloadManager.STATUS_SUCCESSFUL) {
                        Toast.makeText(context, "Download succeeded", Toast.LENGTH_SHORT).show()
                    } else if (status == DownloadManager.STATUS_FAILED) {
                        Toast.makeText(context, "Download failed", Toast.LENGTH_SHORT).show()
                    }
                }
            } else if (DownloadManager.ACTION_NOTIFICATION_CLICKED.equals(intent.action)) {
                Toast.makeText(context, "Notification clicked", Toast.LENGTH_SHORT).show()
            }
        }
    }

}

