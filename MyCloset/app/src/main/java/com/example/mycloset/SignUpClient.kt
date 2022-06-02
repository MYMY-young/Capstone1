package com.example.mycloset

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object SignUpClient {
    private const val baseUrl = "http://3.38.182.144/"
    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service = retrofit.create(SignUpService::class.java)!!
}