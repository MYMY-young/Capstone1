package com.example.mycloset

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class HighlightActivity : AppCompatActivity() {
    var _mainButton: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_highlight)

        _mainButton = findViewById(R.id.high_main_button) as ImageView
        _mainButton!!.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            _mainButton!!.isEnabled = true
        }
    }
}