package com.example.neko.interfece

import com.example.neko.entity.ChangeLpResult
import retrofit2.Call
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface UpdateLikeRateInterface {
    @PUT("cats/{id}")
    fun updateLikeRate(@Path("id") id:Int, @Query("attr") attr:String, @Query("txt") likePoint:Int):Call<ChangeLpResult>
}