package com.example.neko

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.neko.entity.ChangeLpResult
import com.example.neko.entity.Responses
import com.example.neko.interfece.GetCatInfoInterface
import com.example.neko.interfece.UpdateLikeRateInterface
import kotlinx.android.synthetic.main.activity_after.*
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

/* アラーム実行画面 */
class AfterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //スリープ時でも起動できるようにフラグを付与
        window.addFlags(
            WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD or
                WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED or
                WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON or
                WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
        )

        setContentView(R.layout.activity_after)
        //アラーム停止までの制限時間
        val timer=object: CountDownTimer(10000,1000){
            override fun onTick(millisUntilFinished: Long) {
                countDownTxt.text=(millisUntilFinished/1000).toString()
            }

            override fun onFinish() {
                countDownTxt.text="時間切れ"
            }

            fun pause(){
                cancel()
            }

        }
        timer.start()

        //アラーム再生サービスの起動
        startService(Intent(this,MyService::class.java))
        Glide.with(this).load(R.raw.cat_idle).into(gifView)
        Glide.with(this).load(R.raw.cat_akubi).into(gifView2)
        gifView2.visibility= View.INVISIBLE

        stopBtn.setOnClickListener {
            stopService(Intent(this, MyService::class.java))
            timer.pause()
            val likePoint = Integer.parseInt(countDownTxt.text.toString())
            Toast.makeText(this, "+" + likePoint + "好感度", Toast.LENGTH_LONG).show()
            gifView2.visibility = View.VISIBLE
            gifView.visibility = View.INVISIBLE
            getCatInfo(likePoint)
        }
    }
    private fun getCatInfo(likePoint:Int){
        //retrofitとconverterをビルド
        var retrofit= Retrofit.Builder().let{
            it.baseUrl("http://10.0.2.2:8000/api/")
            it.addConverterFactory(GsonConverterFactory.create())
            it.build()
        }
        //interfaceを生成
        var service=retrofit.create(UpdateLikeRateInterface::class.java)
        //interface内のリクエスト送信メソッドを実行
        service.updateLikeRate(1,"likePoint",likePoint).enqueue(CatCallback())
    }

    //コールバックの定義(レスポンスが返却された後の動作を記述)
    inner class CatCallback:Callback<ChangeLpResult>{
        var resMessage:String?=null
        override fun onResponse(call: Call<ChangeLpResult>, response: Response<ChangeLpResult>) {
            val r:String=response.body()!!.result
            resMessage=r
        }

        override fun onFailure(call: Call<ChangeLpResult>, t: Throwable) {
            resMessage="エラー"
        }
    }
}
