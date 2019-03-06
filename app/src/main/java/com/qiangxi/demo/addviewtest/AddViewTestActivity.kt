package com.qiangxi.demo.addviewtest

import android.content.Context
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
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import androidx.appcompat.app.AppCompatActivity
import com.qiangxi.demo.R
import java.io.BufferedOutputStream
import java.io.File
import java.io.FileOutputStream

class AddViewTestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_view_test)

//        "测ghj&^@1~$%搭".toPNG(Environment.getExternalStorageDirectory().absolutePath + "${File.separator}test.png", 5, this)
        "测ghj&^@1~\$%搭".toPNG2(Environment.getExternalStorageDirectory().absolutePath + "${File.separator}test.png")
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
        textPaint.textSize =54F
        textPaint.color = Color.WHITE
        val fontMetrics = textPaint.fontMetricsInt
        val layer = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val c = Canvas(layer)
        c.drawColor(Color.BLUE)
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
    fun String?.toPNG(path: String, padding: Int = 10, ctx: Context) {
        if (TextUtils.isEmpty(this)) {
            return
        }
        var temp = this!!
        if (temp.length > 8) {
            temp = temp.substring(0..7) + "..."
        }
        val finalText = "感谢 $temp"
        val ssb = SpannableStringBuilder(finalText)
        //text
        ssb.setSpan(ForegroundColorSpan(Color.parseColor("#87FFFFFF")), 0, 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        //paint
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        val textPaint = TextPaint(paint)
        textPaint.textSize = dip2pixel(ctx, 16F).toFloat()
        textPaint.color = Color.WHITE
        val textWidth = textPaint.measureText(finalText).toInt()
        val staticLayout = StaticLayout(ssb, textPaint, textWidth, Layout.Alignment.ALIGN_NORMAL, 1F, 0F, true)
        //canvas
        val fontMetrics = textPaint.fontMetricsInt
        val textHeight = fontMetrics.descent - fontMetrics.ascent
        val height = textHeight + padding * 2
        val width = textWidth + height
        val layer = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val c = Canvas(layer)
        val rectF = RectF(0F, 0F, width.toFloat(), height.toFloat())
        paint.shader = LinearGradient(0F, 0F, width.toFloat(), height.toFloat(), Color.parseColor("#F33A23"), Color.parseColor("#5239FE"), Shader.TileMode.CLAMP)
        c.drawRoundRect(rectF, height / 2F, height / 2F, paint)
        c.save()
        c.translate(height / 2F, padding.toFloat() / 2)
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

    fun dip2pixel(context: Context, dpValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
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
