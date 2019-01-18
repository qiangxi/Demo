package com.qiangxi.demo.addviewtest

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.qiangxi.demo.R

class AddViewTestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_view_test)
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
