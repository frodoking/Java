package cn.com.frodo.concurrent.chapter_4;

import cn.com.frodo.concurrent.annotations.GuardedBy;

/**
 * 通过一个私有锁来保护状态
 * Created by frodoking on 2015/1/14.
 */
public class PrivateLock {
    private final Object myLock = new Object();
    @GuardedBy("this")
    Widget widget;

    void someMethod() {
        synchronized (myLock) {
            //访问或修改Widget的状态
        }
    }

    private class Widget {
    }
}
