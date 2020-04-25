package cn.com.frodo.concurrent.chapter_8;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

/**
 * 增加日志和计时功能的线程池
 * Created by frodo on 2015/1/19.
 */
public class TimingThreadPool extends ThreadPoolExecutor {
    private final Logger log = Logger.getLogger("TimingThreadPool");
    private final ThreadLocal<Long> startTime = new ThreadLocal<Long>();
    private final AtomicLong numTasks = new AtomicLong();
    private final AtomicLong totalTime = new AtomicLong();


    public TimingThreadPool() {
        super(1, 1, 0L, TimeUnit.SECONDS, null);
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        super.beforeExecute(t, r);
        log.fine(String.format("Thread %s: start %s", t, r));
        startTime.set(System.nanoTime());
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        super.afterExecute(r, t);
        try {
            long endTime = System.nanoTime();
            long taskTime = endTime - startTime.get();
            numTasks.incrementAndGet();
            totalTime.addAndGet(taskTime);
            log.fine(String.format("Thread %s: end %s, time=%dns", t, r, taskTime));
        } finally {
            super.afterExecute(r, t);
        }
    }

    @Override
    protected void terminated() {
        try {
            log.info(String.format("Terminated: avg time=%dns", totalTime.get() / numTasks.get()));
        } finally {
            super.terminated();
        }
    }
}
