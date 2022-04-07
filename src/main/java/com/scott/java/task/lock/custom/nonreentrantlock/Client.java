package com.scott.java.task.lock.custom.nonreentrantlock;

import lombok.extern.slf4j.Slf4j;

/**
 * @author scott
 * @date 2022/4/4 13:33
 */
@Slf4j
public class Client {

    private static final CustomNonReentrantLock lock = new CustomNonReentrantLock();

    public static void main(String[] args) {
        new Thread(
                () -> {
                    lock.lock();
                    {
                        try {

                            log.info("程序执行中");
                            Thread.sleep(60000);

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } finally {
                            lock.unlock();
                        }
                    }
                }
                , "t1").start();

        new Thread(
                () -> {
                    lock.lock();
                    {
                        try {

                            log.info("程序执行中");
                            Thread.sleep(60000);
                        } catch (Exception e) {

                        }finally {
                            lock.unlock();
                        }
                    }
                }
                , "t2").start();

    }
}