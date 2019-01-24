package com.qiangxi.demo.addviewtest

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import androidx.appcompat.app.AppCompatActivity
import com.qiangxi.demo.R
import java.io.File
import java.io.FileOutputStream

class AddViewTestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_view_test)

//        "测ghj&^@1~$%搭".toPNG(Environment.getExternalStorageDirectory().absolutePath + "${File.separator}test.png")
    }

    /**
     * 文字转png图片
     */
    fun String.toPNG(path: String) {
        val paint = Paint()
        paint.textSize = 14F
        val textWidth = paint.measureText(this).toInt()
        paint.color = Color.RED
        val fontMetrics = paint.fontMetricsInt
        val textHeight = fontMetrics.descent - fontMetrics.ascent
        val layer = Bitmap.createBitmap(textWidth, textHeight, Bitmap.Config.ARGB_8888)
        val c = Canvas(layer)
        c.drawColor(Color.BLUE)
        c.drawText(this, 0F, -fontMetrics.ascent.toFloat(), paint)
        val fos = FileOutputStream(path)
        layer.compress(Bitmap.CompressFormat.PNG, 100, fos)
    }

    fun vvv(v: View) {
        Log.e("AddViewTestActivity", "vvv")
//        val a = AlphaAnimation(0.5F, 1F)
        val a = TranslateAnimation(0F,30F,0F,0F)
        a.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {
            }

            override fun onAnimationEnd(animation: Animation?) {
                Log.e("AddViewTestActivity", "onAnimationEnd")
            }

            override fun onAnimationStart(animation: Animation?) {
                Log.e("AddViewTestActivity", "onAnimationStart")
            }
        })
        a.duration = 3000
        findViewById<View>(R.id.gone).startAnimation(a)
//        val textView = TextView(this)
//        val a = ObjectAnimator.ofFloat(textView, "alpha", 1.0f, 0.6f)
//        a.duration = 3000
//        a.addListener(object : AnimatorListenerAdapter() {
//            override fun onAnimationStart(animation: Animator?) {
//                super.onAnimationStart(animation)
//                Log.e("AddViewTestActivity", "onAnimationStart")
//            }
//
//            override fun onAnimationEnd(animation: Animator?) {
//                super.onAnimationEnd(animation)
//                Log.e("AddViewTestActivity", "onAnimationEnd")
//            }
//        })
//        a.start()


    }
}
