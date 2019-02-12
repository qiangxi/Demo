package com.qiangxi.demo

import org.junit.Test

/**
 * Create By renqiangqiang . 2019/2/11
 */
class KtArray {

    companion object {
        //三维数组
        private  val mArr = Array(5){Array(4){IntArray(5)}}
    }

    @Test
    fun test01() {
        println(mArr.size)
        mArr.forEach {
            it.forEach { value ->
                println("value = $value")
            }
        }
        mArr[0][0][4] = 4

    }
}