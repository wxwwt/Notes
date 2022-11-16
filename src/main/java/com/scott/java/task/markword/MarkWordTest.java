package com.scott.java.task.markword;

import org.junit.Test;
import org.openjdk.jol.info.ClassLayout;

/**
 * @author scott
 * @date 2022/9/28 20:59
 */
public class MarkWordTest {


    /**
     * -XX:InitialHeapSize=132313536 -XX:MaxHeapSize=2117016576 -XX:+PrintCommandLineFlags -XX:+UseCompressedClassPointers -XX:+UseCompressedOops -XX:-UseLargePagesIndividualAllocation -XX:+UseParallelGC
     * java.lang.Object object internals:
     * OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
     * 0     4        (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
     * 4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
     * 8     4        (object header)                           e5 01 00 20 (11100101 00000001 00000000 00100000) (536871397)
     * 12     4        (loss due to the next object alignment)
     * Instance size: 16 bytes
     * Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
     */
    @Test
    public void test1() {
        Object object = new Object();
        System.out.println(ClassLayout.parseInstance(object).toPrintable());
    }

    /**
     * 不使用压缩oops vm参数：-XX:-UseCompressedOops
     * <p>
     * -XX:InitialHeapSize=132313536 -XX:MaxHeapSize=2117016576 -XX:+PrintCommandLineFlags -XX:-UseCompressedOops -XX:-UseLargePagesIndividualAllocation -XX:+UseParallelGC
     * java.lang.Object object internals:
     * OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
     * 0     4        (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
     * 4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
     * 8     4        (object header)                           00 1c 54 17 (00000000 00011100 01010100 00010111) (391388160)
     * 12     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
     * Instance size: 16 bytes
     * Space losses: 0 bytes internal + 0 bytes external = 0 bytes total
     */
    @Test
    public void test2() {
        Object object = new Object();
        System.out.println(ClassLayout.parseInstance(object).toPrintable());
    }

    /**
     * 不使用类型指针压缩 -XX:-UseCompressedClassPointers
     * <p>
     * java.lang.Object object internals:
     * OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
     * 0     4        (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
     * 4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
     * 8     4        (object header)                           00 1c 5f 17 (00000000 00011100 01011111 00010111) (392109056)
     * 12     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
     * Instance size: 16 bytes
     * Space losses: 0 bytes internal + 0 bytes external = 0 bytes total
     */
    @Test
    public void test3() {
        Object object = new Object();
        System.out.println(ClassLayout.parseInstance(object).toPrintable());
    }


    /**
     * 偏向锁状态测试 -XX:BiasedLockingStartupDelay=0
     * <p>
     * java.lang.Object object internals:
     * OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
     * 0     4        (object header)                           05 00 00 00 (00000101 00000000 00000000 00000000) (5)
     * 4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
     * 8     4        (object header)                           e5 01 00 20 (11100101 00000001 00000000 00100000) (536871397)
     * 12     4        (loss due to the next object alignment)
     * Instance size: 16 bytes
     * Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
     */
    @Test
    public void test4() {
        Object object = new Object();
        System.out.println(ClassLayout.parseInstance(object).toPrintable());
    }


    /**
     * 偏向锁状态测试  验证sleep 4100ms后markword的状态
     * java.lang.Object object internals:
     * OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
     * 0     4        (object header)                           05 00 00 00 (00000101 00000000 00000000 00000000) (5)
     * 4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
     * 8     4        (object header)                           e5 01 00 20 (11100101 00000001 00000000 00100000) (536871397)
     * 12     4        (loss due to the next object alignment)
     * Instance size: 16 bytes
     * Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
     */
    @Test
    public void test5() throws InterruptedException {
        Thread.sleep(4100);
        Object object = new Object();
        System.out.println(ClassLayout.parseInstance(object).toPrintable());
    }

    /**
     * 轻量级锁测试：默认开启 -XX:+UseCompressedClassPointers -XX:+UseCompressedOops
     * java.lang.Object object internals:
     * OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
     * 0     4        (object header)                           18 df 38 03 (00011000 11011111 00111000 00000011) (54058776)
     * 4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
     * 8     4        (object header)                           e5 01 00 20 (11100101 00000001 00000000 00100000) (536871397)
     * 12     4        (loss due to the next object alignment)
     * Instance size: 16 bytes
     * Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
     *
     * @throws InterruptedException
     */
    @Test
    public void test6() throws InterruptedException {
        Object object = new Object();
        synchronized (object) {
            System.out.println(ClassLayout.parseInstance(object).toPrintable());
        }
    }

    /**
     * 轻量级锁测试：默认开启 -XX:+UseCompressedClassPointers -XX:+UseCompressedOops
     * 并且去掉偏向锁的延迟： -XX:BiasedLockingStartupDelay=0
     * java.lang.Object object internals:
     * OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
     * 0     4        (object header)                           08 e0 e2 02 (00001000 11100000 11100010 00000010) (48422920)
     * 4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
     * 8     4        (object header)                           e5 01 00 20 (11100101 00000001 00000000 00100000) (536871397)
     * 12     4        (loss due to the next object alignment)
     * Instance size: 16 bytes
     * Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
     *
     * @throws InterruptedException
     */
    @Test
    public void test7() throws InterruptedException {
        Object object = new Object();
        synchronized (object) {
            System.out.println(ClassLayout.parseInstance(object).toPrintable());
        }
    }


    /**
     * 重量级锁测试：-XX:+UseCompressedClassPointers -XX:+UseCompressedOops -XX:BiasedLockingStartupDelay=0
     * 当前线程：线程2java.lang.Object object internals:
     * OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
     * 0     4        (object header)                           8a fe 4c 1a (10001010 11111110 01001100 00011010) (441253514)
     * 4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
     * 8     4        (object header)                           e5 01 00 20 (11100101 00000001 00000000 00100000) (536871397)
     * 12     4        (loss due to the next object alignment)
     * Instance size: 16 bytes
     * Space losses: 0 bytes internal + 4 bytes external = 4 bytes total

     * 当前线程：线程1java.lang.Object object internals:
     * OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
     * 0     4        (object header)                           8a fe 4c 1a (10001010 11111110 01001100 00011010) (441253514)
     * 4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
     * 8     4        (object header)                           e5 01 00 20 (11100101 00000001 00000000 00100000) (536871397)
     * 12     4        (loss due to the next object alignment)
     * Instance size: 16 bytes
     * Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
     *
     * @throws InterruptedException
     */
    @Test
    public void test8() throws InterruptedException {
        Object object = new Object();
        Thread t1 = new Thread(() -> {
            synchronized (object) {
                System.out.println("当前线程：" + Thread.currentThread().getName() + ClassLayout.parseInstance(object).toPrintable());
            }
        }, "线程1"
        );

        Thread t2 = new Thread(() -> {
            synchronized (object) {
                System.out.println("当前线程：" + Thread.currentThread().getName() + ClassLayout.parseInstance(object).toPrintable());
            }
        }, "线程2"
        );


        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }
}