package com.example.mycloset

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.mycloset.databinding.ActivityLoginBinding
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val myName: MyName = MyName("Aleks Haecky")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_login)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.signInButton.setOnClickListener {
            //sign_in_check(it)
            if(TextUtils.isEmpty(Email_input.text.toString())){
                Toast.makeText(applicationContext,"이메일을 입력해주세요.", Toast.LENGTH_SHORT).show()
            }
            else if(TextUtils.isEmpty(Password_input.text.toString())){
                Toast.makeText(applicationContext,"패스워드를 입력해주세요.", Toast.LENGTH_SHORT).show()
            }
            else {
                startActivity(Intent(this, URLActivity::class.java))
            }
        }
    }

    private fun sign_in_chek(view : View){

    }
}