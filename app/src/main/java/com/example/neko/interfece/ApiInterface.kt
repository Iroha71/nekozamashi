package com.example.neko.interfece

import com.example.neko.entity.Results
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface{
    @GET("cat/1/")
    fun apiDemo(): Call<Results>
}