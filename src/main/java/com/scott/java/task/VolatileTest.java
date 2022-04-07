package com.scott.java.task;

import lombok.extern.slf4j.Slf4j;

import static java.lang.Thread.sleep;

/**
 * @author scott
 * @date 2022/3/27 18:33
 */
@Slf4j
public class VolatileTest {
    private static volatile boolean  run = true;

    public static void main(String[] args) throws InterruptedException {
        new Thread(
                () -> {
                    while(run) {
                       log.info("执行中。。。。");
                        System.out.println();
                    }
                }
        ).start();

        sleep(1);
        log.info("停止线程");
       run = false;
    }
}