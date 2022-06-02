package com.example.mycloset

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SignupActivity : AppCompatActivity() {

    var _nameText: EditText? = null
    var _emailText: EditText? = null
    var _passwordText: EditText? = null
    var _reEnterPasswordText: EditText? = null
    var _backButton: ImageView? = null
    var _signupButton: Button? = null
    var _loginLink: TextView? = null




    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        _nameText = findViewById(R.id.input_name) as EditText
        _emailText = findViewById(R.id.input_email) as EditText
        _passwordText = findViewById(R.id.input_password) as EditText
        _reEnterPasswordText = findViewById(R.id.input_reEnterPassword) as EditText

        _backButton = findViewById(R.id.signup_back_button) as ImageView
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

    private fun signup() {
        Log.d(TAG, "Signup")

       if (!validate()) {
           onSignupFailed()
           return
        }
        _signupButton!!.isEnabled = false


        val name = _nameText!!.text.toString()
        val email = _emailText!!.text.toString()
        val password = _passwordText!!.text.toString()
        val reEnterPassword = _reEnterPasswordText!!.text.toString()

        //retrofit2
       // val data : UserInfo = UserInfo(email, name, password)

        val retrofit = Retrofit.Builder()
            .baseUrl("http://3.38.212.229/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val api = retrofit.create(SignUpService::class.java)!!


        val callLoadNotice = api.loadNotice(email, name, password)

        callLoadNotice.enqueue(object : Callback<SignResult> {
            override fun onResponse(
                call: Call<SignResult>,
                response: Response<SignResult>
            ) {


                if(response.isSuccessful) {

                    if(response.body()?.success == "true") {
                        Log.d(TAG, "성공 : ${response.raw()}")
                        Log.d(TAG, "성공 : ${response.body()}")

                        onSignupSuccess()
                    }
                    else{
                        Log.d(TAG, "실패 : ${response.raw()}")
                        Log.d(TAG, "실패 : ${response.body()}")

                        Toast.makeText(baseContext, "${response.body()!!.errMessage}", Toast.LENGTH_LONG).show()

                    }
                }
                else{
                    Log.d(TAG, "실패 : ${response.raw()}")
                    Log.d(TAG, "실패 : ${response.body()}")

                    Toast.makeText(baseContext, "${response.body()!!.errMessage}", Toast.LENGTH_LONG).show()

                }
            }

            override fun onFailure(call: Call<SignResult>, t: Throwable) {
                Log.d(TAG, "실패 : $t")
                Toast.makeText(baseContext, "네트워크 상태를 확인하세요", Toast.LENGTH_LONG).show()
                _signupButton!!.isEnabled = true
            }
        })




    }


    fun onSignupSuccess() {
        Toast.makeText(baseContext, "회원가입이 완료되었습니다", Toast.LENGTH_LONG).show()
        _signupButton!!.isEnabled = true
        //startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    fun onSignupFailed() {
        Toast.makeText(baseContext, "정보를 제대로 입력해주세요", Toast.LENGTH_LONG).show()
        _signupButton!!.isEnabled = true
    }

    fun validate(): Boolean {
        var valid = true

        val name = _nameText!!.text.toString()
        val email = _emailText!!.text.toString()
        val password = _passwordText!!.text.toString()
        val reEnterPassword = _reEnterPasswordText!!.text.toString()

        if (name.isEmpty()) {
            _nameText!!.error = "이름을 입력해주세요"
            valid = false
        } else {
            _nameText!!.error = null
        }

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