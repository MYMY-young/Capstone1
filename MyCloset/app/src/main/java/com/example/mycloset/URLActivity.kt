package com.example.mycloset

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.mycloset.databinding.ActivityUrlBinding

class URLActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUrlBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_url)
    }
}