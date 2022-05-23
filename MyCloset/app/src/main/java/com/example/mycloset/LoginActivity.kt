package com.example.mycloset

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    var _emailText: EditText? = null
    var _passwordText: EditText? = null
    var _loginButton: Button? = null
    var _signupButton: Button? = null

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        _loginButton = findViewById(R.id.sign_in_button) as Button
        _signupButton = findViewById(R.id.sign_up_button) as Button
        _passwordText = findViewById(R.id.Password_input) as EditText
        _emailText = findViewById(R.id.Email_input) as EditText
        _loginButton!!.setOnClickListener { login() }

        _signupButton!!.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
            _signupButton!!.isEnabled = true
        }
    }

    fun saveLogin(loginEmail : String) {
        val pref =getSharedPreferences("loginEmail", MODE_PRIVATE) //shared key 설정
        val edit = pref.edit() // 수정모드
        edit.putString("email", loginEmail) // 값 넣기
        edit.apply() // 적용하기
    }

    fun login() {
        Log.d(TAG, "Login")
        if (!validate()) {
            //onLoginFailed()
            Toast.makeText(applicationContext, "이메일과 패스워드를 제대로 입력해주세요", Toast.LENGTH_LONG).show()
            _loginButton!!.isEnabled = true
            return
        }
        _loginButton!!.isEnabled = false
        val email = _emailText!!.text.toString()
        val password = _passwordText!!.text.toString()

        saveLogin(email)
        _loginButton!!.isEnabled = true
        startActivity(Intent(this, MainActivity::class.java))
    }

    override fun onBackPressed() {
        // Disable going back to the MainActivity
        moveTaskToBack(true)
    }

    fun validate(): Boolean {
        var valid = true

        val email = _emailText!!.text.toString()
        val password = _passwordText!!.text.toString()

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText!!.error = "이메일을 입력해주세요"
            valid = false
        } else {
            _emailText!!.error = null
        }

        if (password.isEmpty() || password.length < 4 || password.length > 10) {
            _passwordText!!.error = "패스워드는 4자에서 10자 사이로 입력해주세요"
            valid = false
        } else {
            _passwordText!!.error = null
        }

        return valid
    }

    companion object {
        private val TAG = "LoginActivity"
        private val REQUEST_SIGNUP = 0
    }
}