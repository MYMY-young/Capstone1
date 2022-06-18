package com.example.mycloset

class VideoInfo(val data : String) {
    var videoname: String = ""
    var videourl: String = ""
    var videopath: String = ""
    val hint = data.split("+")
    init {
        //  "/storage/self/primary/Android/data/com.example.mycloset/files/$filename+$YoutubeURL"
         videoname = "${hint[0].substring(hint[0].lastIndexOf("/")+1)}+${hint[1]}"
        videopath = "${hint[0]}+${hint[1]}"
        videourl = hint[2]
    }
}
