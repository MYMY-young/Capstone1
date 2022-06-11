package com.example.mycloset

import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit


class LoginActivity : AppCompatActivity() {



    var _emailText: EditText? = null
    var _passwordText: EditText? = null
    var _loginButton: Button? = null
    var _signupButton: Button? = null

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        // android downloader
//    var filename : String = ""
       // var downloadManager =  getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        //var downloadId : Long = -1L
        //
        //
//        {
//
//                val file = File(getExternalFilesDir(null), "0+skirt.mp4")
//                val youtubeUrl = "http://54.180.134.56/media/exp8/highlight/0+skirt.mp4"
//                val request = DownloadManager.Request(Uri.parse(youtubeUrl))
//                    .setTitle("Downloading a video")
//                    .setDescription("Downloading Dev Summit")
//                    .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
//                    .setDestinationUri(Uri.fromFile(file))
//                    .setAllowedOverMetered(true)
//                    .setAllowedOverRoaming(true)
//
//                downloadId = downloadManager.enqueue(request)
//            Log.d("DownloadHTTP", "path : " + file.path)

//            val status = getStatus(downloadId, downloadManager)
//            Log.d(TAG, status)
//            Toast.makeText(this, status, Toast.LENGTH_SHORT).show()


//            val intentFilter = IntentFilter()
//            intentFilter.addAction(DownloadManager.ACTION_DOWNLOAD_COMPLETE)
//            intentFilter.addAction(DownloadManager.ACTION_NOTIFICATION_CLICKED)
//            registerReceiver(onDownloadComplete, intentFilter)
//            downloadHTTP(this, "media/exp8/highlight/0+skirt.mp4", "0+skirt.mp4", downloadManager )

      //  }
        //




        _loginButton = findViewById(R.id.sign_in_button) as Button
        _signupButton = findViewById(R.id.sign_up_button) as Button
        _passwordText = findViewById(R.id.Password_input) as EditText
        _emailText = findViewById(R.id.Email_input) as EditText
        _loginButton!!.setOnClickListener {


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
            Toast.makeText(applicationContext, "이메일과 패스워드를 확인하세요", Toast.LENGTH_SHORT).show()
            _loginButton!!.isEnabled = true
            return
        }
        _loginButton!!.isEnabled = false
        val email = _emailText!!.text.toString()
        val password = _passwordText!!.text.toString()


        //retrofit2


        val retrofit = RetrofitClient.getInstance()
        val api = retrofit.create(SignInService::class.java)!!



        val callLoadLogin = api.loadLogin(email, password)


        callLoadLogin.enqueue(object : Callback<SignResult> {
            override fun onResponse(
                call: Call<SignResult>,
                response: Response<SignResult>
            ) {


                Log.d(TAG, "header: ${response.headers()}")
                if(response.isSuccessful){


                   if(response.body()?.success == "true"){
                        Log.d(TAG, "성공 : ${response.raw()}")
                        Log.d(TAG, "성공 : ${response.body()}")

                       Common.init( response.body()?.token.toString())
                       Common.realinit(response.headers().get("Set-Cookie").toString())

                       Log.d(TAG, Common.returnreal())

                       Log.d(TAG, Common.returnCookie())
                        Toast.makeText(baseContext, "로그인 성공", Toast.LENGTH_SHORT).show()
                        loginSuccess(email)
                    }
                   else{
                        Log.d(TAG, "실패 : ${response.raw()}")
                        Log.d(TAG, "실패 : ${response.body()}")

                        Toast.makeText(baseContext, " ${response.body()!!.errMessage}", Toast.LENGTH_SHORT).show()
                        _loginButton!!.isEnabled = true

                    }

               }
                else{
                   Log.d(TAG, "실패 : ${response.raw()}")
                   Toast.makeText(baseContext, "로그인 실패, 서버가 응답하지 않음", Toast.LENGTH_SHORT).show()
                    _loginButton!!.isEnabled = true

                }

            }

            override fun onFailure(call: Call<SignResult>, t: Throwable) {
                Log.d(TAG, "실패 : $t")
                Toast.makeText(baseContext, "서버가 응답하지 않음", Toast.LENGTH_SHORT).show()
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

//    private fun downloadHTTP(context: Context, filePath : String, fileName : String, downloadManager: DownloadManager) {
//        val file = File(getExternalFilesDir(null), fileName)
//        val fileurl = "http://54.180.134.56/${filePath}"
//
//        val request = DownloadManager.Request(Uri.parse(fileurl))
//            .setTitle(fileName)
//            .setDescription("Downloading...")
//            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
//            .setDestinationUri(Uri.fromFile(file))
//            .setAllowedOverMetered(true)
//            .setAllowedOverRoaming(true)
//
//        request.addRequestHeader("cookie", Common.returnCookie())
//       // downloadId = downloadManager.enqueue(request)
//        Log.d("DownloadHTTP", "path : " + file.path)
//
//       // val status = getStatus(downloadId, downloadManager)
//        Log.d(TAG, status)
//        Toast.makeText(this, status, Toast.LENGTH_SHORT).show()
//    }
//
//
//
//
//    private fun getStatus(id: Long, downloadManager: DownloadManager): String {
//        val query: DownloadManager.Query = DownloadManager.Query()
//        query.setFilterById(id)
//        var cursor = downloadManager.query(query)
//        if (!cursor.moveToFirst()) {
//            Log.e(TAG, "Empty row")
//            return "Wrong downloadId"
//        }
//
//        var columnIndex = cursor.getColumnIndex(DownloadManager.COLUMN_STATUS)
//        var status = cursor.getInt(columnIndex)
//        var columnReason = cursor.getColumnIndex(DownloadManager.COLUMN_REASON)
//        var reason = cursor.getInt(columnReason)
//        var statusText: String
//
//        when (status) {
//            DownloadManager.STATUS_SUCCESSFUL -> statusText = "Successful"
//            DownloadManager.STATUS_FAILED -> {
//                statusText = "Failed: $reason"
//            }
//            DownloadManager.STATUS_PENDING -> statusText = "Pending"
//            DownloadManager.STATUS_RUNNING -> statusText = "Running"
//            DownloadManager.STATUS_PAUSED-> {
//                statusText = "Paused: $reason"
//            }
//            else -> statusText = "Unknown"
//        }
//
//
//
//        return statusText
//    }
//
//    private val onDownloadComplete = object : BroadcastReceiver() {
//        override fun onReceive(context: Context, intent: Intent) {
//            val id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1)
//            if (DownloadManager.ACTION_DOWNLOAD_COMPLETE.equals(intent.action)) {
//                if (downloadId == id) {
//                    val query: DownloadManager.Query = DownloadManager.Query()
//                    query.setFilterById(id)
//                    var cursor = downloadManager.query(query)
//                    if (!cursor.moveToFirst()) {
//                        return
//                    }
//
//                    var columnIndex = cursor.getColumnIndex(DownloadManager.COLUMN_STATUS)
//                    var status = cursor.getInt(columnIndex)
//                    if (status == DownloadManager.STATUS_SUCCESSFUL) {
//                        Toast.makeText(context, "Download succeeded", Toast.LENGTH_SHORT).show()
//                    } else if (status == DownloadManager.STATUS_FAILED) {
//                        Toast.makeText(context, "Download failed", Toast.LENGTH_SHORT).show()
//                    }
//                }
//            } else if (DownloadManager.ACTION_NOTIFICATION_CLICKED.equals(intent.action)) {
//                Toast.makeText(context, "Notification clicked", Toast.LENGTH_SHORT).show()
//            }
//        }
//    }

}