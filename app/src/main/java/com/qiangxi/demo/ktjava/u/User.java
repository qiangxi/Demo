package com.qiangxi.demo.ktjava.u;

/**
 * Create By renqiangqiang . 2019/1/15
 */
public class User {
    private String name;

    public User(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "UserCoreImpl{" +
                "name='" + name + '\'' +
                '}';
    }
}