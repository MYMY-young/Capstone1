package com.example.mycloset

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.*
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class URLActivity : YouTubeBaseActivity() {
    var _backButton: ImageView? = null
    var _nextButton: Button? = null
    var _showButton: Button? = null
    var _addressText: EditText? = null
    var _mainButton: ImageView? = null

    val TAG = "URLActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_url)

        _backButton = findViewById(R.id.url_back_button) as ImageView
        _nextButton = findViewById(R.id.url_next_button) as Button
        _showButton = findViewById(R.id.url_show_button) as Button
        _addressText = findViewById(R.id.Address_input) as EditText
        _mainButton = findViewById(R.id.url_main_button) as ImageView

        _backButton!!.setOnClickListener { finish() }
        _nextButton!!.setOnClickListener {
            startActivity(Intent(this, ClothesActivity::class.java))
        }
        _showButton!!.setOnClickListener {
            val videoId = _addressText!!.text.toString().substring(_addressText!!.text.toString().lastIndexOf("/") + 1)
            showVideo(videoId)
            _showButton!!.isEnabled = true
        }
        _mainButton!!.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            _mainButton!!.isEnabled = true
        }
    }

    fun showVideo(videoId: String) {

        //retrofit2 실행, url 전달
        retrofitService()


        val youtubeView = findViewById<YouTubePlayerView>(R.id.youtubeView)
        youtubeView.initialize("develop", object : YouTubePlayer.OnInitializedListener {
            override fun onInitializationSuccess(
                provider: YouTubePlayer.Provider,
                player: YouTubePlayer,
                wasRestored: Boolean
            ) {

                if (!wasRestored) {
                    player.cueVideo(videoId)
                }



            }
            override fun onInitializationFailure(
                provider: YouTubePlayer.Provider?,
                result: YouTubeInitializationResult?
            ) { }
        })
    }

    private fun retrofitService(){
        //retrofit2
        val retrofit = Retrofit.Builder()
            .baseUrl("http://3.38.212.229/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val api = retrofit.create(LinkService::class.java)!!



        val callLoadLogin = api.loadLink(Common.returnCookie(),_addressText?.text.toString())



        callLoadLogin.enqueue(object : Callback<SignResult> {
            override fun onResponse(
                call: Call<SignResult>,
                response: Response<SignResult>
            ) {
                Log.d(TAG, "body: ${response.body()}")
                Log.d(TAG, "raw: ${response.raw()}")

                if(response.isSuccessful){


                    if(response.body()?.success == "true"){
                        Log.d(TAG, "성공 : ${response.raw()}")
                        Log.d(TAG, "성공 : ${response.body()}")
                        Toast.makeText(baseContext, "추출 성공", Toast.LENGTH_LONG).show()



                    }
                    else{
                        Log.d(TAG, "실패 : ${response.raw()}")
                        Log.d(TAG, "실패 : ${response.body()}")

                        Toast.makeText(baseContext, " ${response.body()!!.errMessage}", Toast.LENGTH_LONG).show()
                    }

                }
                else{
                    Log.d(TAG, "OnResponse 실패 : ${response.raw()}")
                    Toast.makeText(baseContext, "서버가 응답하지 않음", Toast.LENGTH_LONG).show()
                }

            }

            override fun onFailure(call: Call<SignResult>, t: Throwable) {
                Log.d(TAG, "OnFailure 실패 : $t")
                Toast.makeText(baseContext, "서버가 응답하지 않음", Toast.LENGTH_LONG).show()

            }
        })
        //end retrofit2
    }
}