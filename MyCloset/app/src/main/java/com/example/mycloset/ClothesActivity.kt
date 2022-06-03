package com.example.mycloset

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class ClothesActivity : AppCompatActivity() {
    var _backButton: ImageView? = null
    var _nextButton: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clothes)

        _backButton = findViewById(R.id.clothes_back_button) as ImageView
        _nextButton = findViewById(R.id.clothes_next_button) as Button

        _backButton!!.setOnClickListener { finish() }
        _nextButton!!.setOnClickListener {
            startActivity(Intent(this, HighlightActivity::class.java))
            _nextButton!!.isEnabled = true
        }
    }
}