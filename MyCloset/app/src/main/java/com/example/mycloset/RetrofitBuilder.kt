package com.example.mycloset


import com.google.gson.GsonBuilder
import okhttp3.*


import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import java.io.IOException
import java.util.concurrent.TimeUnit



object RetrofitClient {
    private var instance: Retrofit? = null
   // private val gson = GsonBuilder().setLenient().create()
    // 서버 주소
    private const val BASE_URL = "http://54.180.134.56/"

    // SingleTon
    fun getInstance(): Retrofit {
        if (instance == null) {
            val okHttpClient = OkHttpClient().newBuilder()
                .retryOnConnectionFailure(false)
                .connectTimeout(3600, TimeUnit.SECONDS)
                .readTimeout(3600, TimeUnit.SECONDS)
                .writeTimeout(3600, TimeUnit.SECONDS)
                .build()

            instance = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client((okHttpClient))
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        return instance!!
    }

    fun resetInstance() {
        instance = null
    }


}


object ApiClient {

    private const val BASE_URL = "http://54.180.134.56/"
    fun getApiClient(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(provideOkHttpClient(AppInterceptor()))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun provideOkHttpClient(interceptor: AppInterceptor): OkHttpClient
            = OkHttpClient.Builder().run {
        retryOnConnectionFailure(false)
        connectTimeout(3600, TimeUnit.SECONDS)
        readTimeout(3600, TimeUnit.SECONDS)
        writeTimeout(3600, TimeUnit.SECONDS)
        addInterceptor(interceptor)
        build()
    }

    class AppInterceptor : Interceptor {
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain) : Response = with(chain) {
            val newRequest = request().newBuilder()
                .addHeader("cookie", Common.returnCookie())
                .build()
            proceed(newRequest)
        }
    }
}





