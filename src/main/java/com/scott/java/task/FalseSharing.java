package com.scott.java.task;

import sun.misc.Contended;

import java.util.concurrent.CountDownLatch;

/**
 * @author shenchuang
 * @date 2022/1/13 9:24 PM
 */
public class FalseSharing {

//    @Contended
    private static class Test {
        //       volatile  long p0, p1, p2, p3, p4, p5, p6;
        public long value = 0;
//      volatile   long q0, q1, q2, q3, q4, q5, q6;
    }


    public static Test[] array = new Test[2];

    static {
        array[0] = new Test();
        array[1] = new Test();
    }

    public static long count = 500_000_000L;

    public static void main(String[] args) throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(2);

        Thread t1 = new Thread(
                () -> {
                    for (int i = 0; i < count; i++) {
                        array[0].value = i;
                    }
                    countDownLatch.countDown();
                }
        );

        Thread t2 = new Thread(
                () -> {
                    for (int i = 0; i < count; i++) {
                        array[1].value = i;
                    }
                    countDownLatch.countDown();
                }
        );

        final long st = System.currentTimeMillis();
        t1.start();
        t2.start();
        countDownLatch.await();
        final long et = System.currentTimeMillis();
        System.out.println("执行耗时:" + (et - st));

    }

}
