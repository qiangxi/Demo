package com.qiangxi.demo.handlerbus

import android.graphics.Matrix
import android.util.Log

/**
 * Create By renqiangqiang . 2019/2/3
 */
class HandlerBusTest {
    companion object {
        private const val TAG = "HandlerBusTest"

        val cc =  FloatArray(9)
    }

    fun test(msg: String) {
        Log.e(TAG, "msg = $msg")
       val m =  Matrix()
        m.setValues(cc)
    }
}