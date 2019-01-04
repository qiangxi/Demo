package com.qiangxi.demo.coroutine

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.qiangxi.demo.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.broadcast
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.launch

/**
 * Create By renqiangqiang(qiang_xi) on 2018/12/25
 */
class CoroutineDemo : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.coroutine_demo)
        findViewById<View>(R.id.test).onClick {
            test1()
        }
    }

    private fun View.onClick(action: suspend () -> Unit) {
        setOnClickListener {
            CoroutineScope(Dispatchers.Default).launch {
                action()
            }
        }
    }



    suspend fun test1() {
        CoroutineScope(Dispatchers.Default).async {

        }
        CoroutineScope(Dispatchers.Default).launch {

        }
        val produce = CoroutineScope(Dispatchers.Default).produce<View> {
            channel.send(Button(this@CoroutineDemo))
        }
        val receive = produce.receive()

//        CoroutineScope(Dispatchers.Default).plus(Dispatchers.Main).launch {
//
//        }
//
        CoroutineScope(Dispatchers.Default).broadcast<View> {

        }
    }

    suspend fun asyncTask(callback: () -> Unit) {
        Thread.sleep(3000)
        callback()
    }
}