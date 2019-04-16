package com.example.neko.interfece

import com.example.neko.entity.Responses
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GetCatInfoInterface{
    @GET("/cat/{id}/")
    fun getInfo(@Path("id") id:Int):Call<Responses>
}