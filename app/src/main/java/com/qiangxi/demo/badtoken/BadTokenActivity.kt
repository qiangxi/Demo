package com.qiangxi.demo.badtoken

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import com.qiangxi.demo.R
import com.qiangxi.demo.viewpostdemo.MainActivity

class BadTokenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bad_token)
    }

    override fun onPause() {
        super.onPause()
        Log.e("rqq", "onPause")
//        DemoFragment().show(supportFragmentManager, "Demo Token")
    }

    override fun onStop() {
        super.onStop()
        Log.e("rqq", "onStop")
//        DemoFragment().show(supportFragmentManager, "Demo Token")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("rqq", "onDestroy")
//        DemoFragment().show(supportFragmentManager, "Demo Token")
    }

    fun ddd(view: View) {
        val i = Intent(this, MainActivity::class.java)
        startActivity(i)
    }
}


class DemoFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.setTitle("Bad Token")
        return dialog
    }
}