package com.qiangxi.demo.dynamicProxy.java;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Create By renqiangqiang . 2019-05-27
 */
public class TestMain {
    public static void main(String[] args) {

        IUserCore proxy = (IUserCore) Proxy.newProxyInstance(TestMain.class.getClassLoader(),
                new Class[]{IUserCore.class}, new UserInvocationHandler(new UserCoreImpl()));
        // proxy.play();
        proxy.say("jj");
        int value = proxy.getValue("121");
        System.out.println("value = " + value);
        System.out.println("proxy type = " + proxy.getClass().getTypeName());
        //
        // IUserCore proxy = (IUserCore) Proxy.newProxyInstance(TestMain.class.getClassLoader(),
        //         new Class[]{IUserCore.class}, new InvocationHandler() {
        //             @Override
        //             public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //                 System.out.println(method.getName() + "...");
        //                 method.invoke(null, args);
        //                 return null;
        //             }
        //         });
        //
        // proxy.play();
        // proxy.say("sda");
    }
}

class UserInvocationHandler implements InvocationHandler {
    private Object impl;

    public UserInvocationHandler(Object obj) {
        impl = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getName().equals("play")) {
            System.out.println("play before");
            method.invoke(impl, args);
            System.out.println("play after");
        } else {
            method.invoke(impl, args);
        }
        if (method.getName().equals("getValue")) {
            String arg = (String) args[0];
            if (arg.equals("121")) {
                method.invoke(impl, args);
                return 999999;
            } else {
                return method.invoke(impl, args);
            }
        }
        return null;
    }
}
