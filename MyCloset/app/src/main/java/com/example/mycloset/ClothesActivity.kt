package com.example.mycloset

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class ClothesActivity : AppCompatActivity() {
    var _backButton: ImageView? = null
    var _nextButton: Button? = null
    var _mainButton: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clothes)

        _backButton = findViewById(R.id.clothes_back_button) as ImageView
        _nextButton = findViewById(R.id.clothes_next_button) as Button
        _mainButton = findViewById(R.id.clothes_main_button) as ImageView

        _backButton!!.setOnClickListener { finish() }
        _nextButton!!.setOnClickListener {
            startActivity(Intent(this, HighlightActivity::class.java))
            _nextButton!!.isEnabled = true
        }
        _mainButton!!.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            _mainButton!!.isEnabled = true
        }
    }
}