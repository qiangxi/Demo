package com.qiangxi.demo.handlerbus

import android.os.Bundle
import android.os.Message
import androidx.appcompat.app.AppCompatActivity
import com.qiangxi.demo.R

class HandlerBusActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_handler_bus)

        val msg = Message()
        msg.what = 1
        msg.obj = 2
        HandlerBus.getDefault().post(msg)
    }

    @HandlerEvent(what = 3)
    fun handleMsg() {
    }
}
