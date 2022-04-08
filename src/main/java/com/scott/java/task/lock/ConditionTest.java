package com.scott.java.task.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author shenchuang
 * @date 2022/4/8 11:13 AM
 */
@Slf4j
public class ConditionTest {

    static final Lock lock = new ReentrantLock();
    static final Condition condition1 = lock.newCondition();
    static final Condition condition2 = lock.newCondition();

    /**
     * t1,t2 如果使用不通的condition通信，t1就不会被唤醒：此时line48打开，line49被注释
     * 结果：
     * 线程：t1, 拿到锁
     * 线程：t1, 进入wait，释放锁
     * 线程：t2, 拿到锁
     * 线程：t2, 进入signal，唤醒所有线程
     * 线程：t2, 执行完毕
     * (t1处于wait状态)
     *
     * t1,t2 如果使用相同的condition通信，都适用condition1，t1就会被唤醒：此时line49行打开，line48被注释
     * 结果：
     * 线程：t1, 拿到锁
     * 线程：t1, 进入wait，释放锁
     * 线程：t2, 拿到锁
     * 线程：t2, 进入signal，唤醒所有线程
     * 线程：t2, 执行完毕
     * 线程：t1, 被唤醒，执行完毕
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        new Thread(
                () -> {
                    lock.lock();
                    try {
                        log.info("线程：{}, 拿到锁", Thread.currentThread().getName());
                        log.info("线程：{}, 进入wait，释放锁", Thread.currentThread().getName());
                        condition1.await();
//                         condition2.await();
                        log.info("线程：{}, 被唤醒，执行完毕", Thread.currentThread().getName());
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        lock.unlock();
                    }
                }
                , "t1").start();
        new Thread(
                () -> {
                    lock.lock();
                    try {
                        log.info("线程：{}, 拿到锁", Thread.currentThread().getName());
                        log.info("线程：{}, 进入signal，唤醒所有线程", Thread.currentThread().getName());
                        condition2.signalAll();
                        log.info("线程：{}, 执行完毕", Thread.currentThread().getName());
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        lock.unlock();
                    }
                }
                , "t2"
        ).start();
    }
}