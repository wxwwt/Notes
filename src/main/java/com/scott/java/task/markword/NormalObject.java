package com.scott.java.task.markword;

import org.junit.Test;
import org.openjdk.jol.info.ClassLayout;

/**
 * @author scott
 * @date 2022/9/28 20:59
 */
public class NormalObject {


    /**
     * -XX:InitialHeapSize=132313536 -XX:MaxHeapSize=2117016576 -XX:+PrintCommandLineFlags -XX:+UseCompressedClassPointers -XX:+UseCompressedOops -XX:-UseLargePagesIndividualAllocation -XX:+UseParallelGC
     * java.lang.Object object internals:
     *  OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
     *       0     4        (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
     *       4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
     *       8     4        (object header)                           e5 01 00 20 (11100101 00000001 00000000 00100000) (536871397)
     *      12     4        (loss due to the next object alignment)
     * Instance size: 16 bytes
     * Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
     */
    @Test
    public  void test1() {
        Object object = new Object();
        System.out.println(ClassLayout.parseInstance(object).toPrintable());
    }

    /**
     * 不使用压缩oops vm参数：-XX:-UseCompressedOops
     *
     * -XX:InitialHeapSize=132313536 -XX:MaxHeapSize=2117016576 -XX:+PrintCommandLineFlags -XX:-UseCompressedOops -XX:-UseLargePagesIndividualAllocation -XX:+UseParallelGC
     * java.lang.Object object internals:
     *  OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
     *       0     4        (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
     *       4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
     *       8     4        (object header)                           00 1c 54 17 (00000000 00011100 01010100 00010111) (391388160)
     *      12     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
     * Instance size: 16 bytes
     * Space losses: 0 bytes internal + 0 bytes external = 0 bytes total
     */
    @Test
    public  void test2() {
        Object object = new Object();
        System.out.println(ClassLayout.parseInstance(object).toPrintable());
    }

    /**
     * 不使用类型指针压缩 -XX:-UseCompressedClassPointers
     *
     * java.lang.Object object internals:
     *  OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
     *       0     4        (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
     *       4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
     *       8     4        (object header)                           00 1c 5f 17 (00000000 00011100 01011111 00010111) (392109056)
     *      12     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
     * Instance size: 16 bytes
     * Space losses: 0 bytes internal + 0 bytes external = 0 bytes total
     */
    @Test
    public  void test3() {
        Object object = new Object();
        System.out.println(ClassLayout.parseInstance(object).toPrintable());
    }


    /**
     * 类型指针开启 对象指针关闭 +XX:-UseCompressedClassPointers -XX:-UseCompressedOops
     *
     * java.lang.Object object internals:
     *  OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
     *       0     4        (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
     *       4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
     *       8     4        (object header)                           00 1c 5f 17 (00000000 00011100 01011111 00010111) (392109056)
     *      12     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
     * Instance size: 16 bytes
     * Space losses: 0 bytes internal + 0 bytes external = 0 bytes total
     *
     * Java HotSpot(TM) 64-Bit Server VM warning: UseCompressedClassPointers requires UseCompressedOops
     */
    @Test
    public  void test4() {
        Object object = new Object();
        System.out.println(ClassLayout.parseInstance(object).toPrintable());
    }
}