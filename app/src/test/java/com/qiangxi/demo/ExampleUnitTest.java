package com.qiangxi.demo;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    private static String fff;

    @Test
    public void addition_isCorrect() {

        //<editor-fold desc="dasdasdasda">

        //<editor-fold desc="dasdasdsads313">
        //</editor-fold>
        try {
            catchError();
        } catch (Throwable e) {
            System.out.println("捕获了error");
        }
    }

    public void catchError() {
        throw new OutOfMemoryError("lalalal");
    }

    @Test
    public void dsd() {
        String s = "546";
        String w = new String("56452");
        cc(s);
        cc(w);
        System.out.println(s);
        System.out.println(w);
    }

    private void cc(String v) {
        v.concat("5434");
    }


    @Test
    public void test33() {
        Object a = null;
        // a = null;
        System.out.println((int) a);

    }
}