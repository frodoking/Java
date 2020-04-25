package cn.com.frodo.concurrent.chapter_4;

import cn.com.frodo.concurrent.annotations.GuardedBy;
import cn.com.frodo.concurrent.annotations.ThreadSafe;

/**
 * 使用java监视器模式的线程安全计数器
 * Created by frodo on 2015/1/14.
 */
@ThreadSafe
public class Counter {
    @GuardedBy("this")
    private long value = 0;

    public synchronized long getValue() {
        return value;
    }

    public synchronized long increment() {
        if (value == Long.MAX_VALUE)
            throw new IllegalStateException("counter overflow");
        return ++value;
    }
}
