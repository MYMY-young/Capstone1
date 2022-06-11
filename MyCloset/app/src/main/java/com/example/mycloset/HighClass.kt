package com.example.mycloset

class HighInfo(val check: String) {
    var imagename: String = ""
    var clothesname: String = ""

    init {
        val hint = check.split("_")
        when (hint[0].toInt()) {
            1 -> {
                when (hint[1].toInt()) {
                    1 -> {
                        imagename = "long_sleeved_outwear1";
                        clothesname = "Long Sleeved Outwear";
                    }
                    2 -> {
                        imagename = "long_sleeved_outwear2";
                        clothesname = "Long Sleeved Outwear";
                    }
                    else -> {
                        imagename = ""; clothesname = ""
                    }
                }
            }
            2 -> {
                when (hint[1].toInt()) {
                    1 -> {
                        imagename = "skirt1";
                        clothesname = "Skirt";
                    }
                    else -> {
                        imagename = ""; clothesname = ""
                    }
                }
            }
            else -> {
                imagename = ""; clothesname = ""
            }
        }
    }
    //fun saveinfo():String{
    //  return String.format("%d_%d_%d",this.kind_num,this.name_num,this.option_num)
    //}
}
