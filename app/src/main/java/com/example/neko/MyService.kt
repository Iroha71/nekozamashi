package com.example.neko

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.widget.Toast

class MyService : Service(){
    private var mp:MediaPlayer?=null
    //override必須
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate(){
        super.onCreate()
    }

    //サービス起動時に実行
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Toast.makeText(this,"アラーム",Toast.LENGTH_SHORT).show()
        mp=MediaPlayer.create(this,R.raw.nyaonyao)
        mp!!.isLooping=true
        mp!!.start()
        return super.onStartCommand(intent, flags, startId)
    }

    //サービス停止時に実行
    override fun onDestroy() {
        mp!!.stop()
        mp!!.release()
    }
}