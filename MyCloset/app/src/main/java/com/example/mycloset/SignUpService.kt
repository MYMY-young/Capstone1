package com.example.mycloset

import retrofit2.Call
import retrofit2.http.*

interface SignUpService {

    @FormUrlEncoded
    @POST("auth/join")
    fun loadNotice(
        @Field("email")email : String,
        @Field("nick")nick : String,
        @Field("password")password : String


    ): Call<SignResult>
}

interface SignInService {

    @FormUrlEncoded
    @POST("auth/login")
   fun loadLogin(
        @Field("email")email : String,
        @Field("password")password : String

    ): Call<SignResult>
}


