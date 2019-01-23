package com.qiangxi.demo.architecture

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.qiangxi.demo.R
import com.qiangxi.demo.architecture.entity.User
import com.qiangxi.demo.architecture.viewmodels.UserViewModel
import com.qiangxi.demo.architecture.viewmodels.UserViewModelFactory

class ArchitectureActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_architecture)
        val model = UserViewModelFactory().create(UserViewModel::class.java)
        model.getUser().observe(this, Observer<User> {
            Log.e("ArchitectureActivity", "it.name = ${it.name}")
        })
    }
}
