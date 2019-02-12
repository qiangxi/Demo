package com.qiangxi.demo.handlerbus

import android.os.Handler
import android.os.Looper
import android.os.Message

/**
 * Create By renqiangqiang(qiang_xi) on 2018/12/14
 */
class HandlerBus private constructor() {

    private val mHandler: Handler = Handler(Looper.getMainLooper())

    companion object {

        fun getDefault(): HandlerBus {
            return HandlerBus()
        }
    }

    fun post(message: Message) {
        mHandler.sendMessage(message)


        mHandler.postDelayed({

        }, 1000)
    }

    fun post(any: Any, id: String) {
    }

    /**
     * 定时任务
     */
    fun timer(message: Message, interval: Int, delay: Long = 0L) {

    }

}