package com.example.neko.interfece

import com.example.neko.entity.Results
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface PostInterface{
    @GET("/cat/{cat_id}/")
    fun getCat(@Path("cat_id") catId:Int): Call<Results>
}