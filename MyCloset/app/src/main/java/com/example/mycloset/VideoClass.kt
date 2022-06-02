package com.example.mycloset

class VideoInfo(val check: String) {
    var videoname: String = ""
    var videourl: String = ""

    init {
        val hint = check.split("_")
        when (hint[0].toInt()) {
            1 -> {
                when (hint[1].toInt()) {
                    1 -> {
                        videoname = "Karen Brit Chick Pants Try On | Summer 2022 Fashion Trends";
                        videourl = "https://youtu.be/OF0YODXMwQw"
                    }
                    2 -> {
                        videoname = "FASHION TRENDS 2022 | On trend but wearable | SUMMER OUTFITS";
                        videourl = "https://youtu.be/cL6TQS7vpws"
                    }
                    else -> {
                        videoname = ""; videourl = ""
                    }
                }
            }
            else -> {
                videoname = ""; videourl = ""
            }
        }
    }
}
