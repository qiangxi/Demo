package com.qiangxi.demo;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import static org.junit.Assert.*;

/**
 * Example local unit test1, which will execute on the development machine (host).
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

    @Test
    public void test56() {
        int[] arr = {1, 3, 5, 6, 2, 4, 1, 87, 45, 34, 67, 12, 10};
        selectionSort(arr);
        StringBuilder sb = new StringBuilder();
        for (int i : arr) {
            sb.append(i).append(",");
        }
        System.out.println(sb.toString());
    }

    /**
     * 选择排序
     */
    public void selectionSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                if (arr[i] < arr[j]) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }

    /**
     * 快速排序
     */
    public void quickSort(int[] arr) {
        int basicIndex = (arr.length / 2);
        int m = arr.length - 1;
        for (int i = 0; i < arr.length; i++) {

        }
    }

    private void group(int[] arr, int basicIndex) {
        int value = arr[basicIndex];//基准值
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < value) {

            } else {

            }
        }
    }

    @Test
    public void test11(){
        System.out.println("BuildBundleDebug".equalsIgnoreCase("BuildBundleDebug"));
        System.out.println("Buildbundledebug".equalsIgnoreCase("BuildBundleDebug"));
        System.out.println("buildbundledebug".equalsIgnoreCase("buildBundleDebug"));
    }
}