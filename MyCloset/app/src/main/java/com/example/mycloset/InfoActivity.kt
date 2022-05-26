package com.example.mycloset

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class InfoActivity : AppCompatActivity() {
    var _backButton: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)

        _backButton = findViewById(R.id.info_back_button) as ImageView
        _backButton!!.setOnClickListener { finish() }
    }
}