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
interface LogoutService {


    @GET("auth/logout")
    fun logOut(
        @Header("authorization")cookie : String

    ): Call<SignResult>


}



interface ClothesService{


    @FormUrlEncoded
    @POST("upload/link")
    fun loadClothes(
        @Header("authorization")cookie : String,
        @Field("link")link : String,
        @Field("short_sleeved_top")short_sleeved_top : Boolean,
        @Field("long_sleeved_top")long_sleeved_top : Boolean,
        @Field("short_sleeved_outwear")short_sleeved_outwear : Boolean,
        @Field("sling_dress")sling_dress : Boolean,
        @Field("vest")vest : Boolean,
        @Field("sling")sling : Boolean,
        @Field("shorts")shorts : Boolean,
        @Field("trousers")trousers : Boolean,
        @Field("skirt")skirt : Boolean,
        @Field("short_sleeved_dress")short_sleeved_dress : Boolean,
        @Field("long_sleeved_dress")long_sleeved_dress : Boolean,
        @Field("vest_dress")vest_dress : Boolean,
        @Field("long_sleeved_outwear")long_sleeved_outwear : Boolean

    ): Call<Clothes>
}

interface UriService{

    @FormUrlEncoded
    @POST("upload/clothes")
    fun loadUri(
        @Header("authorization")cookie : String,
        @Field("name")name : String
    ): Call<UriResult>

}



