package com.scott.java.task.proxy.jdk;

import com.scott.java.task.proxy.Dog;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author scott
 * @date 2022/9/11 15:59
 */
public class DogProxyTest implements InvocationHandler {


    private Dog dog;

    public DogProxyTest(Dog dog) {
        this.dog = dog;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("代理前的方法执行");
        Object object = method.invoke(dog);
        System.out.println("代理后的方法执行");
        return object;
    }


}