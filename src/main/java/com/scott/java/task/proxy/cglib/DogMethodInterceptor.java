package com.scott.java.task.proxy.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author scott
 * @date 2022/9/11 16:34
 */
public class DogMethodInterceptor implements MethodInterceptor {

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("cglib 代理前方法");
       Object object = methodProxy.invokeSuper(o, objects);
        System.out.println("cglib 代理后方法");
        return object;
    }
}