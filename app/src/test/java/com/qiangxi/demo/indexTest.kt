package com.qiangxi.demo

import org.junit.Test

/**
 * Create By renqiangqiang . 2019/3/21
 */

class indexTest {

    @Test
    fun test() {
        val arr = arrayOf("ff", "fs", "dad", "e2", "ada")
        for (i in arr.indices) {
            println("i = $i")
        }
    }
}