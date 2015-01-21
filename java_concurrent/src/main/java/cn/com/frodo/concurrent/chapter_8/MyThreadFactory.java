package cn.com.frodo.concurrent.chapter_8;

import java.util.concurrent.ThreadFactory;

/**
 * Created by frodo on 2015/1/19.
 */
public class MyThreadFactory implements ThreadFactory {
    private final String poolName;

    public MyThreadFactory(String poolName) {
        this.poolName = poolName;
    }

    @Override
    public Thread newThread(Runnable r) {
        return new MyAppThread(r, poolName);
    }
}
