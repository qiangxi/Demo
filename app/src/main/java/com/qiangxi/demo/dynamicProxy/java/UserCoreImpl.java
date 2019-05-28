package com.qiangxi.demo.dynamicProxy.java;

import java.lang.reflect.Method;

/**
 * Create By renqiangqiang . 2019-05-27
 */
public class UserCoreImpl implements IUserCore {

    @Override
    public void say(String str) {
        System.out.println("say: " + str);
    }

    @Override
    public void play() {
        System.out.println("play...");
    }

    @Override
    public int getValue(String play) {
        return play.hashCode();
    }
}
