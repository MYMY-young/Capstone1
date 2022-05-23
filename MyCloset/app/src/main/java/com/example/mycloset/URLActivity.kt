package com.example.mycloset

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class URLActivity : AppCompatActivity() {
    var _doneButton: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_url)

        _doneButton = findViewById(R.id.Done_button) as Button

        _doneButton!!.setOnClickListener {
            startActivity(Intent(this, ClothesActivity::class.java))
            _doneButton!!.isEnabled = true
        }
    }
}