package cn.com.frodo.concurrent.chapter_8;

import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.Semaphore;

/**
 * 使用Semaphore来控制线程池的提交速率
 * Created by frodo on 2015/1/19.
 */
public class BoundedExecutor {
    private final Executor exec;
    private final Semaphore semaphore;

    public BoundedExecutor(Executor exec, Semaphore semaphore) {
        this.exec = exec;
        this.semaphore = semaphore;
    }

    public void submitTask(final Runnable task) throws InterruptedException {
        semaphore.acquire();
        try {
            exec.execute(new Runnable() {
                @Override
                public void run() {
                    task.run();
                }
            });
        } catch (RejectedExecutionException e) {
            semaphore.release();
        }
    }
}
