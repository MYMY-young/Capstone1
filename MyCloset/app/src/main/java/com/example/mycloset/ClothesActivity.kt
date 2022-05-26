package com.example.mycloset

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class ClothesActivity : AppCompatActivity() {
    var _backButton: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clothes)

        _backButton = findViewById(R.id.url_back_button) as ImageView
        _backButton!!.setOnClickListener { finish() }

    }
}