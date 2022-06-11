package com.example.mycloset

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class InfoActivity : AppCompatActivity() {
    var _backButton: ImageView? = null
    var _logoutButton: Button? = null
    val TAG = "InfoActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)

        _backButton = findViewById(R.id.info_back_button) as ImageView
        _logoutButton = findViewById(R.id.info_logout_button) as Button

        _backButton!!.setOnClickListener { finish() }
        _logoutButton!!.setOnClickListener {
          //  var pref = getSharedPreferences("loginEmail", MODE_PRIVATE)
//            var edit = pref.edit()
//
//            edit.clear()
//            edit.commit()

            logoutSuccess()


        }
    }

    private fun logoutSuccess(){
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }
//    private fun retrofitService(){
//        //retrofit2
//
//        val retrofit = RetrofitClient.getInstance()
//        val api = retrofit.create(LogoutService::class.java)!!
//        val cookie = Common.returnCookie()
////, "deflate, gzip, br", "*/*"
//        val callLoadLogin = api.logOut(cookie)
//
//
//        callLoadLogin.enqueue(object : Callback<SignResult> {
//            override fun onResponse(
//                call: Call<SignResult>,
//                response: Response<SignResult>
//            ) {
//                Log.d(TAG, Common.returnCookie())
//
//                Log.d(TAG, "header: ${response.headers()}")
//                if(response.isSuccessful){
//
//
//                    if(response.body()?.success == "true"){
//                        Log.d(TAG, "성공 : ${response.raw()}")
//                        Log.d(TAG, "성공 : ${response.body()}")
//
//                        Toast.makeText(baseContext, "로그아웃", Toast.LENGTH_SHORT).show()
//                        logoutSuccess()
//
//                    }
//                    else{
//                        Log.d(TAG, "실패 : ${response.raw()}")
//                        Log.d(TAG, "실패 : ${response.body()}")
//
//                        Toast.makeText(baseContext, " ${response.body()!!.errMessage}", Toast.LENGTH_SHORT).show()
//
//
//                    }
//
//                }
//                else{
//                    Log.d(TAG, "실패 : ${response.raw()}")
//                    Toast.makeText(baseContext, "로그인 실패, 서버가 응답하지 않음", Toast.LENGTH_SHORT).show()
//
//                }
//
//            }
//
//            override fun onFailure(call: Call<SignResult>, t: Throwable) {
//                Log.d(TAG, "실패 : $t")
//                Toast.makeText(baseContext, "서버가 응답하지 않음", Toast.LENGTH_SHORT).show()
//
//            }
//        })
//    }

}