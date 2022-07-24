package com.scott.java.task.classloading;

/**
 * 被动引用例子二
 * 不会触发任何打印
 * @author scott
 * @date 2022/7/24 20:12
 */
public class NotInitialization2 {

    public static void main(String[] args) {
       SubClass[] arr = new SubClass[3] ;
    }
}

