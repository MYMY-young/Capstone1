package com.example.mycloset

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ClothesActivity : AppCompatActivity() {

    val TAG = "ClothesActivity"
    var _backButton: ImageView? = null
    var _nextButton: Button? = null
    var _mainButton: ImageView? = null
    var result: String = "/"
    var chk : Boolean = false
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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clothes)

        _backButton = findViewById(R.id.clothes_back_button) as ImageView
        _nextButton = findViewById(R.id.clothes_next_button) as Button
        _mainButton = findViewById(R.id.clothes_main_button) as ImageView

        //변수 초기화
        initVariable()


        _short_sleeve_top.setOnClickListener {
            Log.d(TAG, "체크박스 테스트, ${_short_sleeve_top.isChecked()}")
        }

        _backButton!!.setOnClickListener { finish() }

        _nextButton!!.setOnClickListener {

            //선택한 카테고리 이어 붙이기
            if (_short_sleeve_top.isChecked()) {
                 result = result.plus("short sleeve top/")
            }
            if (_long_sleeve_top.isChecked()){
                result = result.plus("long sleeve top/")
            }
            if (_short_sleeve_outwear.isChecked()){
                result = result.plus("short sleeve outwear/")
            }
            if (_sling_dress.isChecked()) {
                result =  result.plus("sling dress/")
            }
            if (_vest.isChecked()){
                result =  result.plus("vest/")
            }
            if (_sling.isChecked()){
                result =   result.plus("sling/")
            }
            if (_shorts.isChecked()){
                result =  result.plus("shorts/")
            }
            if (_trousers.isChecked()){
                result =  result.plus("trousers/")
            }
            if (_skirt.isChecked()){
                result =  result.plus("skirt/")
            }
            if (_short_sleeve_dress.isChecked()){
                result = result.plus("short sleeve dress/")
            }
            if (_long_sleeve_dress.isChecked()){
                result = result.plus("long sleeve dress/")
            }
            if (_vest_dress.isChecked()){
                result = result.plus("vest dress/")
            }
            if (_long_sleeve_outwear.isChecked()){
                result = result.plus("long sleeve outwear/")
            }

            Log.d(TAG, "카테고리 선택 테스트: ${result}")



            retrofitService()

            if(chk) {
                startActivity(Intent(this, HighlightActivity::class.java))
                _nextButton!!.isEnabled = true
                finish()
            }

        }
        _mainButton!!.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            _mainButton!!.isEnabled = true
            finish()
        }
    }

    private fun retrofitService() {
        if(result == "/"){
            Toast.makeText(baseContext, "한 개 이상 옷을 선택해 주세요", Toast.LENGTH_LONG).show()
            _nextButton!!.isEnabled = true
            return
        }
        else {
            chk = true
        }
        //retrofit2
//        val retrofit = Retrofit.Builder()
//            .baseUrl("http://3.38.212.229/")
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//        val api = retrofit.create(ClothesService::class.java)!!
//
//
//
//        val callLoadLogin = api.loadClothes(Common.returnCookie(), result)
//
//
//
//        callLoadLogin.enqueue(object : Callback<SignResult> {
//            override fun onResponse(
//                call: Call<SignResult>,
//                response: Response<SignResult>
//            ) {
//                Log.d(TAG, "body: ${response.body()}")
//                Log.d(TAG, "raw: ${response.raw()}")
//                Log.d(TAG, "select test: ${result}")
//                if (response.isSuccessful) {
//
//
//                    if (response.body()?.success == "true") {
//                        Log.d(TAG, "성공 : ${response.raw()}")
//                        Log.d(TAG, "성공 : ${response.body()}")
//                        Toast.makeText(baseContext, "추출 성공", Toast.LENGTH_LONG).show()
//
//
//                    } else {
//                        Log.d(TAG, "실패 : ${response.raw()}")
//                        Log.d(TAG, "실패 : ${response.body()}")
//
//                        Toast.makeText(
//                            baseContext,
//                            " ${response.body()!!.errMessage}",
//                            Toast.LENGTH_LONG
//                        ).show()
//                    }
//
//                } else {
//                    Log.d(TAG, "OnResponse 실패 : ${response.raw()}")
//                    Toast.makeText(baseContext, "서버가 응답하지 않음", Toast.LENGTH_LONG).show()
//                }
//
//            }
//
//            override fun onFailure(call: Call<SignResult>, t: Throwable) {
//                Log.d(TAG, "OnFailure 실패 : $t")
//                Toast.makeText(baseContext, "서버가 응답하지 않음", Toast.LENGTH_LONG).show()
//
//            }
//        })
        //end retrofit2


    }



    private fun initVariable(){
        _short_sleeve_top = findViewById<CheckBox>(R.id.short_sleeve_top_button)
        _long_sleeve_top = findViewById<CheckBox>(R.id.Long_sleeve_top_button)
        _short_sleeve_outwear = findViewById<CheckBox>(R.id.Short_sleeve_outwear_button)
        _sling_dress = findViewById<CheckBox>(R.id.sling_dress_button)
        _vest = findViewById<CheckBox>(R.id.vest_dress_button)
        _sling = findViewById<CheckBox>(R.id.sling_button)
        _shorts = findViewById<CheckBox>(R.id.shorts_button)
        _trousers = findViewById<CheckBox>(R.id.trouser_button)
        _skirt = findViewById<CheckBox>(R.id.skirt_button)
        _short_sleeve_dress= findViewById<CheckBox>(R.id.short_sleeve_dress_button)
        _long_sleeve_dress= findViewById<CheckBox>(R.id.long_sleeve_dress_button)
        _vest_dress = findViewById<CheckBox>(R.id.vest_dress_button)
        _long_sleeve_outwear = findViewById<CheckBox>(R.id.Long_sleeve_outwear_button)
    }
}

