package com.example.mycloset

import android.animation.ObjectAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {

    var _imageView: ImageView? = null

    // This is the loading time of the splash screen
    private val SPLASH_TIME_OUT:Long = 3000 // 1 sec
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        _imageView = findViewById(R.id.imageView) as ImageView
        textView_M.visibility = View.INVISIBLE
        textView_Y.visibility = View.INVISIBLE
        textView_C.visibility = View.INVISIBLE
        textView_L.visibility = View.INVISIBLE
        textView_O.visibility = View.INVISIBLE
        textView_S.visibility = View.INVISIBLE
        textView_E.visibility = View.INVISIBLE
        textView_T.visibility = View.INVISIBLE

        ObjectAnimator.ofFloat(_imageView, "translationY", 100f).apply {
            duration = 1000
            start()
        }

        Handler(Looper.getMainLooper()).postDelayed({
            textView_M.visibility = View.VISIBLE
        }, 500)

        Handler(Looper.getMainLooper()).postDelayed({
            textView_Y.visibility= View.VISIBLE
        }, 700)

        Handler(Looper.getMainLooper()).postDelayed({
            textView_C.visibility= View.VISIBLE
        }, 900)

        Handler(Looper.getMainLooper()).postDelayed({
            textView_L.visibility= View.VISIBLE
        }, 1100)

        Handler(Looper.getMainLooper()).postDelayed({
            textView_O.visibility= View.VISIBLE
        }, 1300)

        Handler(Looper.getMainLooper()).postDelayed({
            textView_S.visibility= View.VISIBLE
        }, 1500)

        Handler(Looper.getMainLooper()).postDelayed({
            textView_E.visibility= View.VISIBLE
        }, 1700)

        Handler(Looper.getMainLooper()).postDelayed({
            textView_T.visibility= View.VISIBLE
        }, 1900)


     //   val pref = getSharedPreferences("loginEmail", MODE_PRIVATE)
      //  val savedEmail = pref.getString("email", "").toString()

      //  Handler().postDelayed({
         //   if(savedEmail.equals("")){
        //        startActivity(Intent(this, LoginActivity::class.java))
        //        finish()
        //    }else{
         //       startActivity(Intent(this, MainActivity::class.java))
         //       Toast.makeText(this, "로그인 하였습니다", Toast.LENGTH_SHORT).show()
         //       finish()
         //   }
      //  }, SPLASH_TIME_OUT)

        Handler().postDelayed({
            startActivity(Intent(this, LoginActivity::class.java))
                   finish()}, SPLASH_TIME_OUT
        )
    }
}