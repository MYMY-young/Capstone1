package com.example.mycloset

import okhttp3.CookieJar
import java.io.File


data class UserInfo(
    var email: String,
    var nick: String,
    var password: String
)

//data class Result(var result: SignResult)

data class SignResult(
    var success: String = "",
    var errMessage: String = ""
)

class Common{

    companion object {

        private lateinit var cookie : String

        fun init(text : String){
            cookie = text
        }

        fun returnCookie() :String{
            return cookie
        }
    }

}

data class clothes(

    var short_sleeve_top: Boolean,
    var long_sleeve_top: Boolean,
    var short_sleeve_outwear: Boolean,
    var Sling_dress: Boolean,
    var Vest: Boolean,
    var Sling: Boolean,
    var Shorts: Boolean,
    var Trousers: Boolean,
    var Skirt: Boolean,
    var Short_sleeve_dress: Boolean,
    var Long_sleeve_dress: Boolean,
    var Vest_dress: Boolean,
    var Long_sleeve_outwear: Boolean
)
