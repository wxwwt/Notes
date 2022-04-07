package com.scott.java.task;

import java.util.concurrent.CountDownLatch;

/**
 * 伪共享测试代码
 * @author shenchuang
 * @date 2022/1/13 9:24 PM
 */
public class FalseSharing {

    private static class Test {
        private long p1, p2, p3, p4, p5, p6, p7;
        public long value = 0L;
        private long p9, p10, p11, p12, p13, p14, p15;
    }


    public static Test[] array = new Test[2];

    static {
        array[0] = new Test();
        array[1] = new Test();
    }

    public static long count = 2_000_000_000L;

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

        final long st = System.nanoTime();
        t1.start();
        t2.start();
        countDownLatch.await();
        final long et = System.nanoTime();
        System.out.println("执行耗时:" + (et - st) / 1000_0000);

    }

}
