package com.scott.java.task.lock.custom.nonreentrantlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 实现了一个非重入锁（使用AbstractQueuedSynchronizer）
 * 只要实现了同步器的tryAcquire和tryRelease 加锁，解锁方法即可
 * @author scott
 * @date 2022/4/4 13:12
 */
public class CustomNonReentrantLock implements Lock {

    private MySync mySync = new MySync();

    class MySync extends AbstractQueuedSynchronizer {

        @Override
        protected boolean tryAcquire(int arg) {
           if (compareAndSetState(0, 1)) {
               setExclusiveOwnerThread(Thread.currentThread());
               return true;
           }
           return false;
        }

        @Override
        protected boolean tryRelease(int arg) {
           if (getExclusiveOwnerThread() != Thread.currentThread()) {
               throw new IllegalMonitorStateException();
           }
           setExclusiveOwnerThread(null);
           setState(0);
           return true;
        }

        @Override
        protected boolean isHeldExclusively() {
            return super.isHeldExclusively();
        }
    }

    @Override
    public void lock() {
        mySync.acquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
        mySync.release(1);
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}