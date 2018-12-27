package com.qiangxi.demo.handlerbus

/**
 * Create By renqiangqiang(qiang_xi) on 2018/12/14
 */
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
annotation class HandlerEvent(
    val scope: String = "",
    val scheduler: String = "",
    val what: Int
)
