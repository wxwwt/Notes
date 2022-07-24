package com.scott.java.task.classloading;

/**
 * @author scott
 * @date 2022/7/24 20:02
 */
public class ParentLoadingTest {

    static class Parent {
        public static int A = 1;
        static {
            A = 2;
        }
    }

    static class Sub extends Parent {
        public static int B = A;
    }

    /**
     *  输出的是2 父类中的静态语句块要优先于子类的赋值操作
     */
    public static void main(String[] args) {
        System.out.println(Sub.B);
    }
}