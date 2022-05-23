package com.example.mycloset

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_signup.*

class SignupActivity : AppCompatActivity() {

    var _nameText: EditText? = null
    var _addressText: EditText? = null
    var _emailText: EditText? = null
    var _mobileText: EditText? = null
    var _passwordText: EditText? = null
    var _reEnterPasswordText: EditText? = null
    var _backButton: ImageButton? = null
    var _signupButton: Button? = null
    var _loginLink: TextView? = null

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        _nameText = findViewById(R.id.input_name) as EditText
        _addressText = findViewById(R.id.input_address) as EditText
        _emailText = findViewById(R.id.input_email) as EditText
        _mobileText = findViewById(R.id.input_mobile) as EditText
        _passwordText = findViewById(R.id.input_password) as EditText
        _reEnterPasswordText = findViewById(R.id.input_reEnterPassword) as EditText

        _backButton = findViewById(R.id.back_button) as ImageButton
        _signupButton = findViewById(R.id.btn_signup) as Button
        _loginLink = findViewById(R.id.link_login) as TextView

        _backButton!!.setOnClickListener { finish() }
        _signupButton!!.setOnClickListener { signup() }

        _loginLink!!.setOnClickListener {
            val intent = Intent(applicationContext, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    fun signup() {
        Log.d(TAG, "Signup")

        if (!validate()) {
            onSignupFailed()
            return
        }

        _signupButton!!.isEnabled = false

        val name = _nameText!!.text.toString()
        val address = _addressText!!.text.toString()
        val email = _emailText!!.text.toString()
        val mobile = _mobileText!!.text.toString()
        val password = _passwordText!!.text.toString()
        val reEnterPassword = _reEnterPasswordText!!.text.toString()

        onSignupSuccess()
    }


    fun onSignupSuccess() {
        Toast.makeText(baseContext, "회원가입이 완료되었습니다", Toast.LENGTH_LONG).show()
        _signupButton!!.isEnabled = true
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    fun onSignupFailed() {
        Toast.makeText(baseContext, "정보를 제대로 입력해주세요", Toast.LENGTH_LONG).show()
        _signupButton!!.isEnabled = true
    }

    fun validate(): Boolean {
        var valid = true

        val name = _nameText!!.text.toString()
        val address = _addressText!!.text.toString()
        val email = _emailText!!.text.toString()
        val mobile = _mobileText!!.text.toString()
        val password = _passwordText!!.text.toString()
        val reEnterPassword = _reEnterPasswordText!!.text.toString()

        if (name.isEmpty()) {
            _nameText!!.error = "이름을 입력해주세요"
            valid = false
        } else {
            _nameText!!.error = null
        }

        if (address.isEmpty()) {
            _addressText!!.error = "주소를 입력해주세요"
            valid = false
        } else {
            _addressText!!.error = null
        }


        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText!!.error = "이메일을 입력해주세요"
            valid = false
        } else {
            _emailText!!.error = null
        }

        if (mobile.isEmpty()) {
            _mobileText!!.error = "휴대폰 번호를 입력해주세요"
            valid = false
        } else {
            _mobileText!!.error = null
        }

        if (password.isEmpty() || password.length < 4 || password.length > 10) {
            _passwordText!!.error = "패스워드는 4자에서 10자 사이로 입력해주세요"
            valid = false
        } else {
            _passwordText!!.error = null
        }

        if (reEnterPassword.isEmpty() || reEnterPassword.length < 4 || reEnterPassword.length > 10 || reEnterPassword != password) {
            _reEnterPasswordText!!.error = "패스워드가 일치하지 않습니다"
            valid = false
        } else {
            _reEnterPasswordText!!.error = null
        }

        return valid
    }

    companion object {
        private val TAG = "SignupActivity"
    }
}