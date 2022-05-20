package com.example.login_edited

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.example.login_edited.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val myName: MyName = MyName("Aleks Haecky")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

       // binding.hello = myName
       // myName.name = "Seong Jang"
        binding.signInButton.setOnClickListener {
            sign_in_chek(it)
        }
    }

    private fun sign_in_chek(view : View){

    }
}

