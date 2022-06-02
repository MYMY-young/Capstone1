package com.example.mycloset

data class UserInfo(
    var email : String,
    var nick: String,
    var password: String
)

//data class Result(var result: SignResult)

data class SignResult(
    var success : String = "",
    var errMessage : String = ""
)
