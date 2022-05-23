package com.example.mycloset

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var _urlButton: Button? = null
    var _loginButton: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        _urlButton = findViewById(R.id.url_button) as Button
        _loginButton = findViewById(R.id.login_button) as Button

        _urlButton!!.setOnClickListener {
            startActivity(Intent(this, URLActivity::class.java))
            _urlButton!!.isEnabled = true
        }

        _loginButton!!.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            _loginButton!!.isEnabled = true
        }
    }
}