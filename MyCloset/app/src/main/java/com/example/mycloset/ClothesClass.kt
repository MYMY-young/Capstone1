package com.example.mycloset

data class ClothesInfo(val image: String, val clothes: String) {
    var imagename: String = image
    var clothesname: String = clothes
    var urlname: String = "Url: "

    /*
    init {
        val hint = check.split("_")
        when (hint[0].toInt()) {
            1 -> {
                when (hint[1].toInt()) {
                    1 -> {
                        imagename = "white_short_sleeve_top";
                        clothesname = "White Short Sleeve Top";
                        urlname = "https://youtu.be/OF0YODXMwQw"
                    }
                    2 -> {
                        imagename = "blue_long_sleeve_top";
                        clothesname = "Blue Long Sleeve Top";
                        urlname = "https://youtu.be/OF0YODXMwQw"
                    }
                    else -> {
                        imagename = ""; clothesname = ""; urlname = ""
                    }
                }
            }
            2 -> {
                when (hint[1].toInt()) {
                    1 -> {
                        imagename = "blue_jeans";
                        clothesname = "Blue Jeans";
                        urlname = "https://youtu.be/OF0YODXMwQw"
                    }
                    else -> {
                        imagename = ""; clothesname = ""; urlname = ""
                    }
                }
            }
            else -> {
                imagename = ""; clothesname = ""; urlname = ""
            }
        }
    }
    //fun saveinfo():String{
      //  return String.format("%d_%d_%d",this.kind_num,this.name_num,this.option_num)
    //}
    */
}
