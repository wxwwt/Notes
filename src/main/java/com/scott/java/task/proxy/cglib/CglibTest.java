package com.scott.java.task.proxy.cglib;

import com.scott.java.task.proxy.Dog;
import net.sf.cglib.proxy.Enhancer;

/**
 * @author scott
 * @date 2022/9/11 16:31
 */
public class CglibTest {

    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Dog.class);
        enhancer.setCallback(new DogMethodInterceptor());
       Dog dog = (Dog) enhancer.create();
       dog.run();
       dog.sound();
    }
}