package com.example.mycloset

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.ImageView
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
        val youtubeView = itemView?.findViewById<YouTubePlayerView>(R.id.video_youtube) as YouTubePlayerView

        fun bind(item: VideoInfo, context: Context) {
            val videoId = item.videourl.substring(item.videourl.lastIndexOf("/") + 1)
            //val thumbnailId = "https://img.youtube.com/vi/" + videoId + "/default.jpg"
            if(videoId != "") {
                showVideo(youtubeView, videoId)
            }
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

    fun showVideo(youtubeView: YouTubePlayerView, videoId: String) {
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