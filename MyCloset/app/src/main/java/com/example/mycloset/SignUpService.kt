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

interface LinkService {

    @FormUrlEncoded
    @POST("upload/link")
    fun loadLink(
        @Header("cookie")cookie : String,
        @Field("link")link : String

    ): Call<SignResult>
}

interface ClothesService{

    @FormUrlEncoded
    @POST("upload/clothes")
    fun loadClothes(
        @Header("cookie")cookie : String,
        @Field("clothes")clothe : String
    ): Call<SignResult>
}



