package com.scott.java.task.classloading;

/**
 * 被动引用例子一
 * 打印出来的结果是
 * super init
 * 2
 * 没有sub init
 * 子类静态属性和代码块，如果没有被引用到的话就不会被初始化
 * @author scott
 * @date 2022/7/24 20:12
 */
public class NotInitialization {

    public static void main(String[] args) {
        System.out.println(SubClass.value);
    }
}

class SuperClass {
    static {
        System.out.println(" super init");
    }

    public static int value = 123;
}

class SubClass extends SuperClass {


    static {
        System.out.println("sub init");
    }
}