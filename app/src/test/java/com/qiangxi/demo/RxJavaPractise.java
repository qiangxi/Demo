package com.qiangxi.demo;

import org.junit.Test;
import org.reactivestreams.Publisher;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.internal.operators.flowable.FlowableJust;

/**
 * Create By renqiangqiang(qiang_xi) on 2018/9/7
 */
public class RxJavaPractise {

    @Test
    public void test1() {
        Flowable.just(1, 2, 3)
                .map(new Function<Integer, String>() {
                    @Override
                    public String apply(Integer integer) throws Exception {
                        System.out.println(integer + ",map,thread=" + Thread.currentThread());
                        return String.valueOf(integer);
                    }
                })
                .flatMap(new Function<String, Publisher<Boolean>>() {
                    @Override
                    public Publisher<Boolean> apply(String s) throws Exception {
                        System.out.println(s + ",flatMap,thread=" + Thread.currentThread());
                        return Flowable.just("2".equals(s));
                    }
                })
                .filter(new Predicate<Boolean>() {
                    @Override
                    public boolean test(Boolean aBoolean) throws Exception {
                        return !aBoolean;
                    }
                })
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean integer) throws Exception {
                        System.out.println(integer + ",thread=" + Thread.currentThread());
                    }
                });
    }

    @Test
    public void test2() {
        Single.just(1)
                .map(new Function<Integer, String>() {
                    @Override
                    public String apply(Integer integer) throws Exception {
                        return String.valueOf(integer);
                        // throw new Exception("dadasds");
                    }
                })
                .doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        System.out.println("catch:" + throwable.getMessage());
                    }
                })
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String integer) throws Exception {
                        System.out.println(integer);
                    }
                });
    }

    @Test
    public void test3() {
        Observable.interval(0, 1, TimeUnit.SECONDS)
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        System.out.println("thread=" + Thread.currentThread());
                    }
                });
    }

    @Test
    public void test4() {
        Flowable.timer(1, TimeUnit.SECONDS)
                .forEach(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        System.out.println("thread=" + Thread.currentThread());
                    }
                });
    }

    @Test
    public void test5(){

    }
}
