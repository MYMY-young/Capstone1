package com.example.mycloset

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_favorites.*
import kotlinx.android.synthetic.main.activity_highlight.*

var highList = arrayListOf<HighInfo>(
    HighInfo("1_1"),
    HighInfo("1_2")
)

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

        val adapter = HighListAdapter(this, highList)
        HighRecyclerview.adapter = adapter

        val lay = LinearLayoutManager(this)
        HighRecyclerview.layoutManager = lay
        HighRecyclerview.setHasFixedSize(true)
    }
}