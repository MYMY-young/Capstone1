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

        fun setShortsleevedtop(list : ArrayList<String>?){
            ClothesResult?.short_sleeved_top = list
        }
        fun setlong_sleeved_top(list : ArrayList<String>?){
            ClothesResult?.long_sleeved_top = list
        }
        fun setshort_sleeved_outwear(list : ArrayList<String>?){
            ClothesResult?.short_sleeved_outwear = list
        }
        fun setsling_dress(list : ArrayList<String>?){
            ClothesResult?.sling_dress = list
        }
        fun setsling(list : ArrayList<String>?){
            ClothesResult?.sling = list
        }
        fun setvest(list : ArrayList<String>?){
            ClothesResult?.vest = list
        }
        fun setshorts(list : ArrayList<String>?){
            ClothesResult?.shorts = list
        }
        fun settrousers(list : ArrayList<String>?){
            ClothesResult?.trousers = list
        }
        fun setskirt(list : ArrayList<String>?){
            ClothesResult?.skirt = list
            Log.d("DataClass", ClothesResult?.skirt.toString())
        }
        fun setshort_sleeved_dress(list : ArrayList<String>?){
            ClothesResult?.short_sleeved_dress = list
        }
        fun setlong_sleeved_dress(list : ArrayList<String>?){
            ClothesResult?.long_sleeved_dress = list
        }
        fun setvest_dress(list : ArrayList<String>?){
            ClothesResult?.vest_dress = list
        }
        fun setlong_sleeved_outwear(list : ArrayList<String>?){
            ClothesResult?.long_sleeved_outwear = list
        }



        fun getShortsleevedtop(): ArrayList<String>?{

           return ClothesResult?.short_sleeved_top
        }
        fun getlong_sleeved_top(): ArrayList<String>?{
            return ClothesResult?.long_sleeved_top
        }
        fun getshort_sleeved_outwear(): ArrayList<String>?{
            return ClothesResult?.short_sleeved_outwear
        }
        fun getsling_dress(): ArrayList<String>?{
            return ClothesResult?.sling_dress
        }
        fun getsling(): ArrayList<String>?{
            return ClothesResult?.sling
        }
        fun getvest(): ArrayList<String>?{
            return ClothesResult?.vest
        }
        fun getshorts(): ArrayList<String>?{
            return ClothesResult?.shorts
        }
        fun gettrousers(): ArrayList<String>?{
            return  ClothesResult?.trousers
        }
        fun getskirt(): ArrayList<String>?{
            return ClothesResult?.skirt
        }
        fun getshort_sleeved_dress(): ArrayList<String>?{
           return ClothesResult?.short_sleeved_dress
        }
        fun getlong_sleeved_dress(): ArrayList<String>?{
           return ClothesResult?.long_sleeved_dress
        }
        fun getvest_dress(): ArrayList<String>?{
           return ClothesResult?.vest_dress
        }
        fun getlong_sleeved_outwear(): ArrayList<String>?{
            return ClothesResult?.long_sleeved_outwear
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


