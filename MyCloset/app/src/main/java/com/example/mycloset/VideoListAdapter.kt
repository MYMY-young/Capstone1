package com.example.mycloset

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.ImageView
import android.widget.VideoView
import androidx.recyclerview.widget.RecyclerView
import com.example.mycloset.VideoListAdapter.Holder
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView

class VideoListAdapter(val context: Context, val itemList : ArrayList<VideoInfo>) :
    RecyclerView.Adapter<Holder>() {
    inner class Holder(itemView: View?) : RecyclerView.ViewHolder(itemView!!){
        //val thumbnailView = itemView?.findViewById<ImageView>(R.id.video_image)
        val name = itemView?.findViewById<TextView>(R.id.video_name)
        val url = itemView?.findViewById<TextView>(R.id.video_url)
        val youtubeView = itemView?.findViewById<VideoView>(R.id.video_youtube)
        val _video_detail = itemView?.findViewById<Button>(R.id.video_detail)

//        val path = Uri.parse("/storage/self/primary/Android/data/com.example.mycloset/files/person_01+long_sleeved_outwear.mp4")
//        _videoView.setVideoURI(path)
//        _videoView.requestFocus()
//        _videoView.start()

        fun bind(item: VideoInfo, context: Context) {

                val path = Uri.parse(item.videopath)
                youtubeView?.setVideoURI(path)
                youtubeView?.requestFocus()
                youtubeView?.start()
                _video_detail?.setOnClickListener { youtubeView?.start() }
                name?.text = item.videoname
                url?.text = "URL: " + item.videourl

        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.activity_video_contacts, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(itemList[position],context)
    }


}