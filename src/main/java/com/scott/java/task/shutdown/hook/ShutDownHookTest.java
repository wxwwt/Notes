package com.scott.java.task.shutdown.hook;

import org.assertj.core.util.Lists;
import org.junit.Test;

import java.util.List;

/**
 * @author scott
 * @date 2023/6/18 15:34
 */
public class ShutDownHookTest {

    @Test
    public void test1() {
        // 测试正常退出的情况
        Runtime.getRuntime().addShutdownHook(
                new Thread(
                        () -> {
                            System.out.println("hook1 执行了");
                        })
        );
    }


    @Test
    public void test2() {
        // 测试Hook执行顺序是否真的无序
        Runtime.getRuntime().addShutdownHook(
                new Thread(
                        () -> {
                            System.out.println("hook1 执行了");
                        })
        );

        Runtime.getRuntime().addShutdownHook(
                new Thread(
                        () -> {
                            System.out.println("hook2 执行了");
                        })
        );
    }


    @Test
    public void test3() {
        // 测试kill -9 会执行Hook吗
        Runtime.getRuntime().addShutdownHook(
                new Thread(
                        () -> {
                            System.out.println("hook1 执行了");
                        })
        );

        while(true) {

        }

    }

    @Test
    public void test4() {
        // 测试oom时 会执行Hook吗
        Runtime.getRuntime().addShutdownHook(
                new Thread(
                        () -> {
                            System.out.println("hook1 执行了");
                        })
        );

        /**
         *
         java.lang.OutOfMemoryError: GC overhead limit exceeded

         at com.scott.java.task.shutdown.hook.ShutDownHookTest.test4(ShutDownHookTest.java:74)
         。。。省略不重要的日志

         hook1 执行了

         */
        List<Object> list = Lists.newArrayList();
        while(true) {
           list.add(new ShutDownHookTest());
        }
    }

    @Test
    public void test5() {
        // 测试移除Hook后，会执行Hook吗
        Thread thread = new Thread(() -> {
            System.out.println("hook1 执行了");
        });

        Runtime.getRuntime().addShutdownHook(thread);
        Runtime.getRuntime().removeShutdownHook(thread);
    }

    @Test
    public void test6() {
        // 测试执行halt方法后，会执行Hook吗
        Thread thread = new Thread(() -> {
            System.out.println("hook1 执行了");
        });

        Runtime.getRuntime().addShutdownHook(thread);
        Runtime.getRuntime().halt(111);
    }

    @Test
    public void test7() {
        // 测试已经执行Hook时，还能添加新的hook吗
        Thread thread = new Thread(() -> {
            System.out.println("hook1 执行了");
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                System.out.println("hook2 执行了");
            }));
        });

        Runtime.getRuntime().addShutdownHook(thread);
    }

    @Test
    public void test8() {
        // 测试重复注册后，会执行Hook吗
        Thread thread = new Thread(() -> {
            System.out.println("hook1 执行了");
        });

        Runtime.getRuntime().addShutdownHook(thread);
        Runtime.getRuntime().addShutdownHook(thread);
    }

    @Test
    public void test9() {
        // 测试线程执行后，还能被注册Hook吗
        Thread thread = new Thread(() -> {
            System.out.println("hook1 执行了");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        thread.start();
        Runtime.getRuntime().addShutdownHook(thread);
    }


}