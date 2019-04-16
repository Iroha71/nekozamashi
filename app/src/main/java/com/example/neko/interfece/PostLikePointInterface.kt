package com.example.neko.interfece

import com.example.neko.entity.Responses
import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.Path

interface PostLikePointInterface{
    @POST("/cat/1/")
    fun setLikePoint(@Path("id") id:Int,@Path("add_lp") likePoint:Int):Call<Responses>
}