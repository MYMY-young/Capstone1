package com.example.mycloset

import android.app.DownloadManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.youtube.player.internal.i
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class ClothesActivity : AppCompatActivity() {

    var downloadId : Long = -1L

    val TAG = "ClothesActivity"
    var _backButton: ImageView? = null
    var _nextButton: Button? = null
    var _mainButton: ImageView? = null
    var result: String = "/"
    var chk: Boolean = false
    lateinit var _short_sleeve_top: CheckBox
    lateinit var _long_sleeve_top: CheckBox
    lateinit var _short_sleeve_outwear: CheckBox
    lateinit var _sling_dress: CheckBox
    lateinit var _vest: CheckBox
    lateinit var _sling: CheckBox
    lateinit var _shorts: CheckBox
    lateinit var _trousers: CheckBox
    lateinit var _skirt: CheckBox
    lateinit var _short_sleeve_dress: CheckBox
    lateinit var _long_sleeve_dress: CheckBox
    lateinit var _vest_dress: CheckBox
    lateinit var _long_sleeve_outwear: CheckBox

    var short_sleeve_top: Boolean = false
    var long_sleeve_top: Boolean = false
    var short_sleeve_outwear: Boolean = false
    var sling_dress: Boolean = false
    var vest: Boolean = false
    var sling: Boolean = false
    var shorts: Boolean = false
    var trousers: Boolean = false
    var skirt: Boolean = false
    var short_sleeve_dress: Boolean = false
    var long_sleeve_dress: Boolean = false
    var vest_dress: Boolean = false
    var long_sleeve_outwear: Boolean = false

    // android downloader
//    var filename : String = ""

    //

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clothes)

        _backButton = findViewById(R.id.clothes_back_button) as ImageView
        _nextButton = findViewById(R.id.clothes_next_button) as Button
        _mainButton = findViewById(R.id.clothes_main_button) as ImageView


        //변수 초기화
        initVariable()
       // downloadManager =  getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager


        _short_sleeve_top.setOnClickListener {
            Log.d(TAG, "체크박스 테스트, ${_short_sleeve_top.isChecked}")
        }

        _backButton!!.setOnClickListener { finish() }

        _nextButton!!.setOnClickListener {

            //선택한 카테고리 이어 붙이기
            if (_short_sleeve_top.isChecked) {
                short_sleeve_top = true
                result = result.plus("short sleeve top/")
            } else {
                short_sleeve_top = false
            }

            if (_long_sleeve_top.isChecked) {
                long_sleeve_top = true
                result = result.plus("long sleeve top/")
            } else {
                long_sleeve_top = false
            }

            if (_short_sleeve_outwear.isChecked) {
                short_sleeve_outwear = true
                result = result.plus("short sleeve outwear/")
            } else {
                short_sleeve_outwear = true
            }

            if (_sling_dress.isChecked) {
                sling_dress = true
                result = result.plus("sling dress/")
            } else {
                sling_dress = false
            }

            if (_vest.isChecked) {
                vest = true
                result = result.plus("vest/")
            } else {
                vest = false
            }

            if (_sling.isChecked) {
                sling = true
                result = result.plus("sling/")
            } else {
                sling = false
            }

            if (_shorts.isChecked) {
                shorts = true
                result = result.plus("shorts/")
            } else {
                shorts = false
            }

            if (_trousers.isChecked) {
                trousers = true
                result = result.plus("trousers/")
            } else {
                trousers = false
            }

            if (_skirt.isChecked) {
                skirt = true
                result = result.plus("skirt/")
            } else {
                skirt = false
            }

            if (_short_sleeve_dress.isChecked) {
                short_sleeve_dress = true
                result = result.plus("short sleeve dress/")
            } else {
                short_sleeve_dress = false
            }

            if (_long_sleeve_dress.isChecked) {
                long_sleeve_dress = true
                result = result.plus("long sleeve dress/")
            } else {
                long_sleeve_dress = false
            }

            if (_vest_dress.isChecked) {
                vest_dress = true
                result = result.plus("vest dress/")
            } else {
                vest_dress = false
            }

            if (_long_sleeve_outwear.isChecked) {
                long_sleeve_outwear = true
                result = result.plus("long sleeve outwear/")
            } else {
                long_sleeve_outwear = false
            }

            Log.d(TAG, "카테고리 선택 테스트: ${result}")

//            Handler().postDelayed(Runnable {
//                //딜레이 후 시작할 코드 작성
//            }, 600) // 0.6초 정도 딜레이를 준 후 시작

           // success()
            retrofit2Service()


        }
        _mainButton!!.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            _mainButton!!.isEnabled = true

            finish()
        }
    }
//        request.addRequestHeader("cookie", Common.returnCookie())


    private fun downloadService(uri : String,  filename : String){
       var downloadManager : DownloadManager =  getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager

        val file = File(getExternalFilesDir(null), filename)
        val Url = "http://54.180.134.56/media${uri}"
        val request = DownloadManager.Request(Uri.parse(Url))
            .setTitle(filename)
            .setDescription(filename)
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            .setDestinationUri(Uri.fromFile(file))
            .setAllowedOverMetered(true)
            .setAllowedOverRoaming(true)
            .addRequestHeader("authorization", Common.returnCookie())


        downloadId = downloadManager.enqueue(request)
        Log.d("DownloadHTTP", "path : " + file.path)

    }


    private fun retrofit2Service() {
        if (result == "/") {
            Toast.makeText(baseContext, "한 개 이상 옷을 선택해 주세요", Toast.LENGTH_SHORT).show()
            _nextButton!!.isEnabled = true
            return
        }
        Toast.makeText(baseContext, "하이라이트 추출중...", Toast.LENGTH_SHORT).show()

        //retrofit2
        RetrofitClient.resetInstance()
        val retrofit = RetrofitClient.getInstance()
        val api = retrofit.create(ClothesService::class.java)!!
       // val api = ApiClient.getApiClient().create(ClothesService::class.java)

        Log.d(TAG, "레트로핏 전에 쿠키 확인 ${Common.returnCookie()}")
        Log.d(TAG, "레트로핏 전에 url 확인 ${Common.returnVideo()}")

        val callLoadLogin = api.loadClothes(Common.returnCookie(),Common.returnVideo(),
            short_sleeve_top,
            long_sleeve_top,
            short_sleeve_outwear,
            sling_dress,
            vest,
            sling,
            shorts,
            trousers,
            skirt,
            short_sleeve_dress,
            long_sleeve_dress,
            vest_dress,
            long_sleeve_outwear
        )


        callLoadLogin.enqueue(object : Callback<Clothes> {
            override fun onResponse(
                call: Call<Clothes>,
                response: Response<Clothes>
            ) {
                Log.d(TAG, "body: ${response.body()}")
                Log.d(TAG, "raw: ${response.raw()}")
                Log.d(TAG, "select test: ${result}")
                if (response.isSuccessful) {
                    var body = response.body()

                    if (_short_sleeve_top.isChecked) {
                        var temp = body?.short_sleeved_top
                        Log.d("downcontent", temp.toString())
                        if (temp != null) {
                            for (i in temp) {
                                downloadService(i,  i.substring(i.lastIndexOf("/") + 1))
                                Log.d("downcontent", "파일이름: ${i.substring(i.lastIndexOf("/") + 1)}")
                            }
                        }
                    }

                    if (_long_sleeve_top.isChecked) {
                        var temp = body?.long_sleeved_top
                        Log.d("downcontent", temp.toString())

                        if (temp != null) {
                            for (i in temp) {
                                downloadService(i,  i.substring(i.lastIndexOf("/") + 1))

                            }
                        }
                    }

                    if (_short_sleeve_outwear.isChecked) {
                        var temp = body?.short_sleeved_outwear
                        Log.d("downcontent", temp.toString())

                        if (temp != null) {
                            for (i in temp) {
                                downloadService(i,  i.substring(i.lastIndexOf("/") + 1))

                            }
                        }
                    }

                    if (_sling_dress.isChecked) {
                        var temp = body?.sling_dress
                        Log.d("downcontent", temp.toString())

                        if (temp != null) {
                            for (i in temp) {
                                downloadService(i,  i.substring(i.lastIndexOf("/") + 1))

                            }
                        }
                    }

                    if (_vest.isChecked) {
                        var temp = body?.vest
                        Log.d("downcontent", temp.toString())

                        if (temp != null) {
                            for (i in temp) {
                                downloadService(i,  i.substring(i.lastIndexOf("/") + 1))

                            }
                        }
                    }

                    if (_sling.isChecked) {
                        var temp = body?.sling
                        Log.d("downcontent", temp.toString())

                        if (temp != null) {
                            for (i in temp) {
                                downloadService(i,  i.substring(i.lastIndexOf("/") + 1))

                            }
                        }
                    }

                    if (_shorts.isChecked) {
                        var temp = body?.shorts
                        Log.d("downcontent", temp.toString())

                        if (temp != null) {
                            for (i in temp) {
                                downloadService(i,  i.substring(i.lastIndexOf("/") + 1))

                            }
                        }
                    }

                    if (_trousers.isChecked) {
                        var temp = body?.trousers
                        Log.d("downcontent", temp.toString())

                        if (temp != null) {
                            for (i in temp) {
                                downloadService(i, i.substring(i.lastIndexOf("/") + 1))

                            }
                        }
                    }

                    if (_skirt.isChecked) {
                        var temp = body?.skirt
                        Log.d("downcontent", temp.toString())

                        if (temp != null) {
                            for (i in temp) {
                                downloadService(i,  i.substring(i.lastIndexOf("/") + 1))
                                Log.d("downcontent", "파일이름: ${i.substring(i.lastIndexOf("/") + 1)}")

                            }
                        }
                    }

                    if (_short_sleeve_dress.isChecked) {
                        var temp = body?.short_sleeved_dress
                        Log.d("downcontent", temp.toString())

                        if (temp != null) {
                            for (i in temp) {
                                downloadService(i,  i.substring(i.lastIndexOf("/") + 1))

                            }
                        }
                    }

                    if (_long_sleeve_dress.isChecked) {
                        var temp = body?.long_sleeved_dress
                        Log.d("downcontent", temp.toString())

                        if (temp != null) {
                            for (i in temp) {
                                downloadService(i,  i.substring(i.lastIndexOf("/") + 1))

                            }
                        }
                    }

                    if (_vest_dress.isChecked) {
                        var temp = body?.vest_dress
                        Log.d("downcontent", temp.toString())

                        if (temp != null) {
                            for (i in temp) {
                                downloadService(i,  i.substring(i.lastIndexOf("/") + 1))

                            }
                        }
                    }

                    if (_long_sleeve_outwear.isChecked) {
                        var temp = body?.long_sleeved_outwear
                        Log.d("downcontent", temp.toString())

                        if (temp != null) {
                            for (i in temp) {
                                downloadService(i,  i.substring(i.lastIndexOf("/") + 1))

                            }
                        }
                    }
                    Log.d("다운로드 매니저", "성공")
//                    Common.setShortsleevedtop(body?.short_sleeved_top)
//                    Common.setlong_sleeved_top(body?.long_sleeved_top)
//                    Common.setshort_sleeved_outwear(body?.short_sleeved_outwear)
//                    Common.setsling_dress(body?.sling_dress)
//                    Common.setsling(body?.sling)
//                    Common.setvest(body?.vest)
//                    Common.setshorts(body?.shorts)
//                    Common.settrousers(body?.trousers)
//                    Common.setskirt(body?.skirt)
//                    Common.setshort_sleeved_dress(body?.short_sleeved_dress)
//                    Common.setlong_sleeved_dress(body?.long_sleeved_dress)
//                    Common.setvest_dress(body?.vest_dress)
//                    Common.setlong_sleeved_outwear(body?.long_sleeved_dress)

                     Toast.makeText(baseContext, "하이라트 생성 완료", Toast.LENGTH_SHORT).show()






                 //   downcontent()

                 //  success()

                } else {
                    Log.d(TAG, "OnResponse 실패 : ${response.raw()}")
                    Log.d(TAG, "header: ${response.headers()}")
                    Log.d(TAG, "cookie: ${Common.returnCookie()}")


                   // Toast.makeText(baseContext, "서버가 응답하지 않음", Toast.LENGTH_SHORT).show()
                }

            }

            override fun onFailure(call: Call<Clothes>, t: Throwable) {
                Log.d(TAG, "OnFailure 실패 : $t")
                Toast.makeText(baseContext, "서버가 응답하지 않음", Toast.LENGTH_SHORT).show()

            }
        })
        //end retrofit2


    }

    private fun success(){
        startActivity(Intent(this, HighlightActivity::class.java))
        _nextButton!!.isEnabled = true
        finish()
    }

    private fun initVariable() {
        _short_sleeve_top = findViewById<CheckBox>(R.id.short_sleeve_top_button)
        _long_sleeve_top = findViewById<CheckBox>(R.id.Long_sleeve_top_button)
        _short_sleeve_outwear = findViewById<CheckBox>(R.id.Short_sleeve_outwear_button)
        _sling_dress = findViewById<CheckBox>(R.id.sling_dress_button)
        _vest = findViewById<CheckBox>(R.id.vest_dress_button)
        _sling = findViewById<CheckBox>(R.id.sling_button)
        _shorts = findViewById<CheckBox>(R.id.shorts_button)
        _trousers = findViewById<CheckBox>(R.id.trouser_button)
        _skirt = findViewById<CheckBox>(R.id.skirt_button)
        _short_sleeve_dress = findViewById<CheckBox>(R.id.short_sleeve_dress_button)
        _long_sleeve_dress = findViewById<CheckBox>(R.id.long_sleeve_dress_button)
        _vest_dress = findViewById<CheckBox>(R.id.vest_dress_button)
        _long_sleeve_outwear = findViewById<CheckBox>(R.id.Long_sleeve_outwear_button)
    }

}

