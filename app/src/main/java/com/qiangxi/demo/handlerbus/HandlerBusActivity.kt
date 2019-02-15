package com.qiangxi.demo.handlerbus

import android.os.Bundle
import android.os.Environment
import androidx.appcompat.app.AppCompatActivity
import com.qiangxi.demo.R
import com.qiangxi.demo.util.ZipUtil
import kotlinx.android.synthetic.main.activity_handler_bus.*
import java.io.File

class HandlerBusActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_handler_bus)

        val path = Environment.getExternalStorageDirectory().absolutePath + File.separator
        zip.setOnClickListener {
            ZipUtil.zip("${path}yymobile/face_model_avatar_output", "${path}yyzip/avatar.zip")
        }
        unzip.setOnClickListener {
            ZipUtil.unzip("${path}yyzip/avatar.zip", "${path}yyunzip/avatar")
        }
//        val msg = Message()
//        msg.what = 1
//        msg.obj = 2
//        HandlerBus.getDefault().post(msg)
    }

//    @HandlerEvent(what = 3)
//    fun handleMsg() {
//    }
}
