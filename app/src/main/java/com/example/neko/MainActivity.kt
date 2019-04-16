package com.example.neko

import android.content.Intent
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import com.example.neko.entity.Results
import com.example.neko.interfece.ApiInterface
import com.example.neko.interfece.PostInterface
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var gitMovie: Int=R.raw.cat_idle
        var gitMovie2: Int=R.raw.cat_nyao
        gifView2.setVisibility(View.INVISIBLE)
        Glide.with(this).load(gitMovie2).into(gifView2)
        Glide.with(this).load(gitMovie).into(gifView)

        sendbtn.setOnClickListener {
            //retrofitで非同期通信
            var retrofit=Retrofit.Builder().let {
                it.baseUrl("http://10.0.2.2:3000/")
                it.addConverterFactory(GsonConverterFactory.create())
                it.build()
            }
            var service=retrofit.create(PostInterface::class.java)
            service.getCat(1).enqueue(ApiCallBack())
        }
        gifView.setOnClickListener {
            gifView2.setVisibility(View.VISIBLE)
        }

        sendBtn.setOnClickListener {
            val intent=Intent(this,AlarmActivity::class.java)
            startActivity(intent)
        }

    }
    //apiコールバックの定義
    inner class ApiCallBack:retrofit2.Callback<Results>{
        override fun onResponse(call: Call<Results>, response: Response<Results>) {
            val r:String=response.body()!!.name+response.body()!!.like_rate
            Log.d("d",r)
            jsontext.text=r
        }

        override fun onFailure(call: Call<Results>, t: Throwable) {
                jsontext.text="error!!" +t.toString()
        }
    }
}
