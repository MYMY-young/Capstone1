package com.example.mycloset

class HighInfo(val check: String) {
    var imagename: String = ""
    var category: String = ""
    var path: String = ""
    var videourl: String = ""

    // "$category+/storage/self/primary/Android/data/com.example.mycloset/files/$filename+$YoutubeURL\n"
    init {
        val hint = check.split("+")
        imagename = hint[1].substring(hint[1].lastIndexOf("/") + 1)
        path = hint[1]
        videourl = hint[2]
        when (hint[0]) {
            "short_sleeved_top" -> category = "Short Sleeved Top"
            "long_sleeved_top" -> category = "Long Sleeved Top"
            "short_sleeved_outwear" -> category = "Short Sleeved Outwear"
            "sling_dress" -> category = "Sling Dress"
            "sling" -> category = "Sling"
            "vest" -> category = "Vest"
            "shorts" -> category = "Shorts"
            "trousers" -> category = "Trousers"
            "skirt" -> category = "Skirt"
            "short_sleeved_dress" -> category = "Short Sleeved Dress"
            "long_sleeved_dress" -> category = "Long Sleeved Dress"
            "vest_dress" -> category = "Vest Dress"
            "long_sleeved_outwear" -> category = "Long Sleeved Outwear"
        }
    }
    //fun saveinfo():String{
    //  return String.format("%d_%d_%d",this.kind_num,this.name_num,this.option_num)
    //}
}
