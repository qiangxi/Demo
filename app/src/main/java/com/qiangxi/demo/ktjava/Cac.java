package com.qiangxi.demo.ktjava;

/**
 * Create By renqiangqiang . 2019-07-24
 */
public class Cac<T> {
    T[] t;
    public static void main(String[] args) {


        Test1 test2 = new Test1();
        System.out.println("test2 = " + test2.getClass().getSimpleName());
        Test1 test1 = new Test1() {
            @Override
            public void test() {
                System.out.println("hashcode = " + this.getClass().getSimpleName());
            }
        };
        System.out.println("test1-hashcode = " + test1.getClass().getSimpleName());
        test1.test();
    }
}
