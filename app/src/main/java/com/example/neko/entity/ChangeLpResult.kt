package com.example.neko.entity

data class ChangeLpResult(
    var result:String,
    var info:Info
)

data class Info(
    var name:String,
    var like_rate:Int
)