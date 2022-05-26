package com.example.mycloset

class ClothesInfo(val check:String) {

    val name:String
    val kind: String
    val imagename: String
    val price: Int
    val option1: String
    val option2: String
    val option3: String

    var kind_num:Int=0
    var name_num:Int=0
    var option_num:Int=0

    init {
        val hint = check.split("_")
        kind_num=hint[0].toInt()
        name_num=hint[1].toInt()
        option_num=hint[2].toInt()

        when (hint[0].toInt()){
            1-> {
                this.kind = "toy"
                when (hint[1].toInt()){
                    1->{this.name="공"; this.imagename = null.toString(); this.price = 500; this.option1 =
                        "빨간색"; this.option2 = "파란색"; this.option3 = ""}
                    2->{this.name="오뚝이" ; this.imagename = null.toString(); this.price = 500;this.option1 =
                        "1";this.option2 = "2"; this.option3 = "3"}
                    3->{this.name="터그"; this.imagename = null.toString(); this.price = 500;this.option1 =
                        "1";this.option2 = "2"; this.option3 = "3"}
                    4->{this.name="낚시대";this.imagename = null.toString();this.price = 500;this.option1 =
                        "1";this.option2 = "2";this.option3 = "3"}
                    5->{this.name="인형";this.imagename = null.toString();this.price = 500;this.option1 =
                        "1";this.option2 = "2";this.option3 = "3"}
                    else -> {name="";imagename="";price=0;option1="";option2="";option3=""}
                }
            }
            2-> {
                this.kind = "house"
                when(hint[1].toInt()){
                    1-> {this.name="기본 쿠션";this.imagename = "freecushion"; this.price = 500; this.option1 =
                        "빨간색"; this.option2 = "파란색"; this.option3 = null.toString()}
                    2->{this.name="Pink 베이직 홈";this.imagename = "pinkbasichouse"; this.price =
                        500;this.option1 = "1";this.option2 = "2"; this.option3 = "3"}
                    3->{this.name="Blue 베이직 홈"; this.imagename = "bluebasichouse";this.price =
                        500;this.option1 = "1";this.option2 = "2";this.option3 = "3"}
                    4->{this.name="Brown 베이직 홈"; this.imagename = "bluebasichouse";this.price =
                        500;this.option1 = "1";this.option2 = "2";this.option3 = "3"}
                    5-> {this.name="프린세스 홈"; this.imagename = "princesshouse";this.price = 500;this.option1 =
                        "1";this.option2 = "2";this.option3 = "3"}
                    6->{ this.name="Premium 캣타워";this.imagename = "cattowerpre";this.price = 500;this.option1 =
                        "1";this.option2 = "2";this.option3 = "3"}
                    7->{this.name="멍샴푸";this.imagename = null.toString(); this.price =
                        500; this.option1 = "빨간색"; this.option2 = "파란색"; this.option3 = null.toString()}
                    else -> {name="";imagename="";price=0;option1="";option2="";option3=""}

                }
            }
            3-> {
                this.kind = "household"
                when(hint[1].toInt()) {
                    1->{this.name="멍샴푸";this.imagename = null.toString(); this.price =
                        500; this.option1 = "빨간색"; this.option2 = "파란색"; this.option3 = null.toString()}
                    2->{this.name="냥샴푸";this.imagename = null.toString(); this.price =
                        500;this.option1 = "1";this.option2 = "2"; this.option3 = "3" }
                    3->{this.name="칫솔";this.imagename = "toothbrush";this.price =
                        500;this.option1 = "1";this.option2 = "2";this.option3 = "3"}
                    4->{this.name="치약";this.imagename = "toothpaste";this.price =
                        500;this.option1 = "1";this.option2 = "2";this.option3 = "3"}
                    5->{this.name="배변패드"; this.imagename = "pad";this.price =
                        500;this.option1 = "1";this.option2 = "2";this.option3 = "3"}
                    else -> {name="";imagename="";price=0;option1="";option2="";option3=""}
                }
            }
            4-> {
                this.kind = "cloth"
                when(hint[1].toInt()) {
                    1->{this.name="목줄";this.imagename = "brownlead"; this.price =
                        500; this.option1 = "초록색"; this.option2 = "파란색"; this.option3 = "갈색"}
                    2->{this.name="심플 옷";this.imagename = "whitecloth"; this.price =
                        500;this.option1 = "1";this.option2 = "2"; this.option3 = "3"}
                    3->{this.name="옷옷";this.imagename = "tigercloth";this.price = 500;this.option1 =
                        "1";this.option2 = "2";this.option3 = "3"}
                    else -> {name="";imagename="";price=0;option1="";option2="";option3=""}
                }
            }
            5-> {
                this.kind = "feed"
                when(hint[1].toInt()){
                    1-> {this.name="강아지 사료";this.imagename = "dogyum"; this.price = 500; this.option1 =
                        "빨간색"; this.option2 = "파란색"; this.option3 = null.toString() }
                    2->{this.name="고양이 사료";this.imagename = "catyum"; this.price = 500;this.option1 =
                        "1";this.option2 = "2"; this.option3 = "3"}
                    3->{this.name="Premium 강아지 사료"; this.imagename = "dogyumpre";this.price = 500;this.option1 =
                        "1";this.option2 = "2";this.option3 = "3" }
                    4->{this.name="Premium 고양이 사료"; this.imagename = "catyumpre";this.price = 500;this.option1 =
                        "1";this.option2 = "2";this.option3 = "3"}
                    5-> {this.name="개껌 (강아지용)"; this.imagename = "doggum";this.price = 500;this.option1 =
                        "1";this.option2 = "2";this.option3 = "3"}
                    6->{ this.name="닭가슴살 츄르 (고양이용)";this.imagename = "yellowchu";this.price = 500;this.option1 =
                        "1";this.option2 = "2";this.option3 = "3"}
                    7->{this.name="연어 츄르";this.imagename = "orangechu";this.price = 500;this.option1 =
                        "1";this.option2 = "2";this.option3 = "3"}
                    8->{this.name="참치 츄르 (고양이 용)";this.imagename = null.toString();this.price =
                        500;this.option1 = "1";this.option2 = "2";this.option3 = "3"}
                    else -> {name="";imagename="";price=0;option1="";option2="";option3=""}
                }
            }
            else -> {name="";kind="";imagename="";price=0;option1="";option2="";option3=""}
        }
    }

    fun saveinfo():String{
        return String.format("%d_%d_%d",this.kind_num,this.name_num,this.option_num)
    }
}
