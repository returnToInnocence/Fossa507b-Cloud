package com.zenq.cloud507b.config;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MultithreadingConfig {
    //进程同步互斥相关对象
    public Lock lock = null;
    public Condition condition = null;
    public MultithreadingConfig(){
        this.lock = new ReentrantLock();
        this.condition = lock.newCondition();
    }
}
