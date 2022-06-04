package com.example.mycloset

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.franmontiel.persistentcookiejar.PersistentCookieJar
import com.franmontiel.persistentcookiejar.cache.SetCookieCache
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class LoginActivity : AppCompatActivity() {

    //쿠키설정
   // var cookieJar = PersistentCookieJar(SetCookieCache(), SharedPrefsCookiePersistor(this))



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
        _loginButton!!.setOnClickListener {
            //임시 테스트
            _loginButton!!.isEnabled = true
            startActivity(Intent(this, MainActivity::class.java))
            finish()
            //



            login() }

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

    private fun login() {
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


        //retrofit2
      // val client = OkHttpClient.Builder().cookieJar(cookieJar).build()

        val retrofit = Retrofit.Builder()
            .baseUrl("http://3.38.212.229/")
         //  .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val api = retrofit.create(SignInService::class.java)!!


        val callLoadLogin = api.loadLogin(email, password)


        callLoadLogin.enqueue(object : Callback<SignResult> {
            override fun onResponse(
                call: Call<SignResult>,
                response: Response<SignResult>
            ) {
                Common.init( response.headers().get("Set-Cookie").toString())
                Log.d(TAG, Common.returnCookie())

                Log.d(TAG, "header: ${response.headers()}")
                if(response.isSuccessful){


                   if(response.body()?.success == "true"){
                        Log.d(TAG, "성공 : ${response.raw()}")
                        Log.d(TAG, "성공 : ${response.body()}")

                        Toast.makeText(baseContext, "로그인 성공", Toast.LENGTH_LONG).show()
                        loginSuccess(email)
                    }
                   else{
                        Log.d(TAG, "실패 : ${response.raw()}")
                        Log.d(TAG, "실패 : ${response.body()}")

                        Toast.makeText(baseContext, " ${response.body()!!.errMessage}", Toast.LENGTH_LONG).show()
                        _loginButton!!.isEnabled = true

                    }

               }
                else{
                   Log.d(TAG, "실패 : ${response.raw()}")
                   Toast.makeText(baseContext, "로그인 실패, 서버가 응답하지 않음", Toast.LENGTH_LONG).show()
                    _loginButton!!.isEnabled = true

                }

            }

            override fun onFailure(call: Call<SignResult>, t: Throwable) {
                Log.d(TAG, "실패 : $t")
                Toast.makeText(baseContext, "서버가 응답하지 않음", Toast.LENGTH_LONG).show()
                _loginButton?.isEnabled = true
            }
        })


    }

    private fun loginSuccess(email : String){
        saveLogin(email)
        _loginButton!!.isEnabled = true
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override fun onBackPressed() {
        // Disable going back to the MainActivity
        moveTaskToBack(true)
    }

   private fun validate(): Boolean {
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