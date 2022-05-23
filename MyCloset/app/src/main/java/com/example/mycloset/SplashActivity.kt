package com.example.mycloset

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast

class SplashActivity : AppCompatActivity() {

    // This is the loading time of the splash screen
    private val SPLASH_TIME_OUT:Long = 3000 // 1 sec
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val pref = getSharedPreferences("loginEmail", MODE_PRIVATE)
        val savedEmail = pref.getString("email", "").toString()

        Handler().postDelayed({
            if(savedEmail.equals("")){
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }else{
                startActivity(Intent(this, MainActivity::class.java))
                Toast.makeText(this, "로그인 하였습니다", Toast.LENGTH_SHORT).show()
                finish()
            }
        }, SPLASH_TIME_OUT)
    }
}