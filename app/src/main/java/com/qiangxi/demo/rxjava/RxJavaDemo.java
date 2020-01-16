package com.qiangxi.demo.rxjava;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import io.reactivex.schedulers.Schedulers;

/**
 * Create By renqiangqiang . 2019-07-23
 */
public class RxJavaDemo {

    public static void main(String[] args) {
        // test1();
        // test2();
        // test3();
        test4();
    }

    private static void test1() {
        Schedulers.io().createWorker().schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("test1, thread = " + Thread.currentThread().getName());
            }
        });
    }

    private static void test2() {
        Schedulers.io().scheduleDirect(new Runnable() {
            @Override
            public void run() {
                System.out.println("test2, thread = " + Thread.currentThread().getName());
            }
        });
    }

    private static void test3() {
        final CountDownLatch latch = new CountDownLatch(1);
        Schedulers.io().scheduleDirect(new Runnable() {
            @Override
            public void run() {
                latch.countDown();
                System.out.println("test3, thread = " + Thread.currentThread().getName());
            }
        }, 1, TimeUnit.SECONDS);

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void test4() {
        final CountDownLatch latch = new CountDownLatch(1);
        Schedulers.io().schedulePeriodicallyDirect(new Runnable() {
            @Override
            public void run() {
                // latch.countDown();
                System.out.println("test3, thread = " + Thread.currentThread().getName());
            }
        }, 1, 1, TimeUnit.SECONDS);

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
