package com.scott.java.task.classloading;

/**
 * @author scott
 * @date 2022/7/24 20:07
 */
public class Test {
    /**
     * 如果去掉下面的sout的注释就会报错
     * 静态语句是按照顺序来加载的
     * 所以i在静态代码块可以赋值，但是不能访问，
     * 因为静态代码块是在i定义前访问的，是不行的。
     * 如果i在静态代码块前定义就是可以访问的了
     */
    static {
        i = 0;

//        System.out.println(i);
    }
    public  static int  i = 1;

}