package com.qiangxi.demo.addviewtest

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Paint
import android.graphics.RectF
import android.graphics.Shader
import android.os.Bundle
import android.os.Environment
import android.text.Layout
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.StaticLayout
import android.text.TextPaint
import android.text.TextUtils
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.qiangxi.demo.R
import com.qiangxi.test.MainActivity
import java.io.BufferedOutputStream
import java.io.File
import java.io.FileOutputStream

class AddViewTestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_view_test)

//        "闲了蛋的超人不会飞".toPNG(Environment.getExternalStorageDirectory().absolutePath + "${File.separator}test1.pngg")
//        "闲了蛋".toPNG(Environment.getExternalStorageDirectory().absolutePath + "${File.separator}test1.png")
        "闲了蛋的超人不会飞".toPNG2(Environment.getExternalStorageDirectory().absolutePath + "${File.separator}test1.pngg")
        "闲了蛋的超人不会飞".toPNG(Environment.getExternalStorageDirectory().absolutePath + "${File.separator}test.png")
        "闲了蛋".toPNG(Environment.getExternalStorageDirectory().absolutePath + "${File.separator}test1.png")
//        "闲了蛋的超人不会飞".toPNG2(Environment.getExternalStorageDirectory().absolutePath + "${File.separator}test.png")
    }


    fun String?.toPNG2(path: String) {
        if (TextUtils.isEmpty(this)) {
            return
        }
        val height = 100
        val width = 600
        var temp = this!!
        if (temp.length > 8) {
            temp = temp.substring(0..7) + "..."
        }
        val finalText = "感谢 $temp"
        val textPaint = TextPaint(Paint.ANTI_ALIAS_FLAG)
        textPaint.textSize = 54F
        textPaint.style = Paint.Style.FILL_AND_STROKE
        textPaint.strokeWidth = 2F
        textPaint.color = Color.WHITE
        val fontMetrics = textPaint.fontMetricsInt
        val layer = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val c = Canvas(layer)
//        c.drawColor(Color.BLUE)
        val y = height / 2 - fontMetrics.descent / 2 - fontMetrics.ascent / 2
        c.drawText(finalText, 0F, y.toFloat(), textPaint)
        var bos: BufferedOutputStream? = null
        try {
            bos = BufferedOutputStream(FileOutputStream(path))
            layer.compress(Bitmap.CompressFormat.PNG, 100, bos)
            bos.flush()
        } catch (t: Throwable) {
            Log.i("AvatarDownloadQueue", "String.toPNG occur exception, e = $t")
        } finally {
            bos?.close()
            layer.recycle()
        }
    }

    /**
     * 文字转png图片
     */
    fun String?.toPNG(path: String) {
        if (TextUtils.isEmpty(this)) {
            return
        }
        var temp = this!!
        if (temp.length > 8) {
            temp = temp.substring(0..7) + "..."
        }
        val padding = dp2px(3F)
        val finalText = "感谢 $temp"
        val ssb = SpannableStringBuilder(finalText)
        //text
        ssb.setSpan(ForegroundColorSpan(Color.parseColor("#87FFFFFF")), 0, 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        //paint
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        val textPaint = TextPaint(paint)
        textPaint.textSize = dp2sp(11F).toFloat()
        textPaint.color = Color.WHITE
        val textWidth = textPaint.measureText(finalText).toInt()
        val staticLayout = StaticLayout(ssb, textPaint, textWidth, Layout.Alignment.ALIGN_NORMAL, 1F, 0F, true)
        //canvas
        val fm = textPaint.fontMetrics
        val height = fm.descent - fm.ascent + padding * 2
        val width = textWidth + height
        Log.e("rqq", "width = $width, height = $height, textWidth = $textWidth")
        val layer = Bitmap.createBitmap(width.toInt(), height.toInt(), Bitmap.Config.ARGB_8888)
        val c = Canvas(layer)
        val rectF = RectF(0F, 0F, width, height)
        paint.shader = LinearGradient(0F, 0F, width, height, Color.parseColor("#F33A23"), Color.parseColor("#5239FE"), Shader.TileMode.CLAMP)
        c.drawRoundRect(rectF, height / 2F, height / 2F, paint)
        c.save()
        c.translate(height / 2F, padding / 2F)
        staticLayout.draw(c)
        c.restore()
        var bos: BufferedOutputStream? = null
        try {
            bos = BufferedOutputStream(FileOutputStream(path))
            layer.compress(Bitmap.CompressFormat.PNG, 100, bos)
            bos.flush()
        } catch (t: Throwable) {
            Log.i("AvatarDownloadQueue", "String.toPNG occur exception, e = $t")
        } finally {
            bos?.close()
            layer.recycle()
        }
    }

    fun dp2sp(dpValue: Float): Int {
        val scale = resources.displayMetrics.scaledDensity
        return (dpValue * scale + 0.5f).toInt()
    }

    fun dp2px(dpValue: Float): Int {
        val scale = resources.displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }

    fun vvv(v: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)

//        Log.e("AddViewTestActivity", "vvv")
//        val a = AlphaAnimation(0.5F, 1F)
        val a = TranslateAnimation(0F, 30F, 0F, 0F)
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
//        val a = TranslateAnimation(0F,30F,0F,0F)
//        a.setAnimationListener(object : Animation.AnimationListener {
//            override fun onAnimationRepeat(animation: Animation?) {
//            }
//
//            override fun onAnimationEnd(animation: Animation?) {
//                Log.e("AddViewTestActivity", "onAnimationEnd")
//            }
//
//            override fun onAnimationStart(animation: Animation?) {
//                Log.e("AddViewTestActivity", "onAnimationStart")
//            }
//        })
//        a.duration = 3000
//        findViewById<View>(R.id.gone).startAnimation(a)
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
