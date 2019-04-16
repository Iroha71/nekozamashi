package com.example.neko

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class AlarmBroadcastReceiver:BroadcastReceiver(){
    override fun onReceive(context: Context?, intent: Intent?) {

        //アラームマネージャーが設定した時刻に実行
        //AfterActivityにフラグを付与して起動
        val intent=Intent(context,AfterActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context!!.startActivity(intent)
    }
}