package cn.com.frodo.concurrent.chapter_8;

import cn.com.frodo.concurrent.annotations.GuardedBy;
import cn.com.frodo.concurrent.annotations.ThreadSafe;

import java.util.concurrent.CountDownLatch;

/**
 * Result-bearing latch used by ConcurrentPuzzleSolver
 * Created by frodo on 2015/1/21.
 */
@ThreadSafe
public class ValueLatch<T> {
    @GuardedBy("this")
    private T value = null;
    private final CountDownLatch done = new CountDownLatch(1);

    public boolean isSet() {
        return done.getCount() == 0;
    }

    public synchronized void setValue(T newValue) {
        if (!isSet()) {
            value = newValue;
            done.countDown();
        }
    }

    public T getValue() throws InterruptedException {
        done.await();
        synchronized (this) {
            return value;
        }
    }
}
