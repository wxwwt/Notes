package com.scott.java.task.proxy.jdk;

import com.scott.java.task.proxy.Animal;
import com.scott.java.task.proxy.Dog;

import java.lang.reflect.Proxy;

/**
 * @author scott
 * @date 2022/9/11 16:12
 */
public class Client {

    public static void main(String[] args) {
        Animal animal = (Animal) Proxy.newProxyInstance(Animal.class.getClassLoader(), new Class[]{Animal.class}, new DogProxyTest(new Dog()));
        animal.run();
        animal.sound();
    }
}