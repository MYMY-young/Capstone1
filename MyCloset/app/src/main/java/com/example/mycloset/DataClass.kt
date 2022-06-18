package com.example.mycloset

import android.util.Log
import com.google.android.youtube.player.internal.i


data class UserInfo(
    var email: String,
    var nick: String,
    var password: String
)

data class SignResult(
    var success: String = "",
    var errMessage: String = "",
    var token : String
)

class Common {

    companion object {

        private lateinit var cookie: String
        private lateinit var VideoLink: String
        private lateinit var DownloadLink: String
        private lateinit var realcookie : String
        var ClothesResult: Clothes? = null

        fun realinit(data : String){
            realcookie = data
        }
        fun returnreal() : String{
            return realcookie
        }




        fun setVideo(link: String) {
            VideoLink = link
        }

        fun returnVideo(): String {
            return VideoLink
        }

        fun init(text: String) {
            cookie = text
        }

        fun returnCookie(): String {
            return cookie
        }
    }

}

data class UriResult(
    var success: String = "",
    var errMessage: String = "",
    var Uri: String = ""
)

data class Clothes(
    var short_sleeved_top: ArrayList<String>?,
    var long_sleeved_top: ArrayList<String>?,
    var short_sleeved_outwear: ArrayList<String>?,
    var sling_dress: ArrayList<String>?,
    var sling: ArrayList<String>?,
    var vest: ArrayList<String>?,
    var shorts: ArrayList<String>?,
    var trousers: ArrayList<String>?,
    var skirt: ArrayList<String>?,
    var short_sleeved_dress: ArrayList<String>?,
    var long_sleeved_dress: ArrayList<String>?,
    var vest_dress: ArrayList<String>?,
    var long_sleeved_outwear: ArrayList<String>?
)


