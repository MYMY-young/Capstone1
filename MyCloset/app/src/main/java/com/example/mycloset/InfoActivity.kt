package com.example.mycloset

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class InfoActivity : AppCompatActivity() {
    var _backButton: ImageView? = null
    var _logoutButton: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)

        _backButton = findViewById(R.id.info_back_button) as ImageView
        _logoutButton = findViewById(R.id.info_logout_button) as Button

        _backButton!!.setOnClickListener { finish() }
        _logoutButton!!.setOnClickListener {
            var pref = getSharedPreferences("loginEmail", MODE_PRIVATE)
            var edit = pref.edit()

            edit.clear()
            edit.commit()
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}