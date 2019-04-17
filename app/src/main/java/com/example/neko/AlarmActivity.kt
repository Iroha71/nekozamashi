package com.example.neko

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_alarm.*
import com.example.neko.AlarmBroadcastReceiver
import java.util.*

class AlarmActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alarm)

        val catIdleImage:Int=R.raw.cat_idle
        val catNyaoImage:Int=R.raw.cat_nyao
        Glide.with(this).load(catIdleImage).into(catView)
        Glide.with(this).load(catNyaoImage).into(catViewNyao)
        catViewNyao.visibility=View.INVISIBLE
        startBtn.setOnClickListener {
            //タイマーをセット
            var calendar:Calendar= Calendar.getInstance()
            calendar.timeInMillis=System.currentTimeMillis()
            calendar.add(Calendar.SECOND,5)

            //レシーバの起動
            val intent=Intent(this,AlarmBroadcastReceiver::class.java)
            val pending=PendingIntent.getBroadcast(this,0,intent,0)

            //アラームマネージャーの設定
            var am:AlarmManager=getSystemService(Context.ALARM_SERVICE) as AlarmManager
            am.setExact(AlarmManager.RTC_WAKEUP,calendar.timeInMillis,pending)

            catViewNyao.visibility=View.VISIBLE
            Toast.makeText(this,"SetAlarm",Toast.LENGTH_SHORT).show()
        }
    }
}
