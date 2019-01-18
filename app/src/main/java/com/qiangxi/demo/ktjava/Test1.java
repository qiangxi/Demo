package com.qiangxi.demo.ktjava;

/**
 * Create By renqiangqiang . 2019/1/15
 */
public class Test1 {

    public void test() {
        com.qiangxi.demo.ktjava.User user = new com.qiangxi.demo.ktjava.User("zhangsan");
        com.qiangxi.demo.ktjava.u.User user1 = Test2.INSTANCE.toJavaUser(user);
        System.out.println(user1);
    }
}

