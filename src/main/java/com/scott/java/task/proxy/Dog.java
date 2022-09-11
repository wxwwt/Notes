package com.scott.java.task.proxy;

/**
 * @author scott
 * @date 2022/9/11 16:01
 */
public class Dog implements Animal {
    @Override
    public void run() {
        System.out.println("小狗跑的飞快");
    }

    @Override
    public void sound() {
        System.out.println("小狗汪汪叫");
    }
}