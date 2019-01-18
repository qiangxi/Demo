package com.qiangxi.demo.ktjava

import com.qiangxi.demo.ktjava.u.User as JavaUser

/**
 * Create By renqiangqiang . 2019/1/15
 */
object Test2 {

    fun toJavaUser(u: User?): JavaUser? = u?.run {
        JavaUser(name)
    }
}

class User(var name: String)