package cn.com.frodo.concurrent.chapter_7;

import cn.com.frodo.concurrent.chapter_5.LaunderThrowable;

import java.math.BigInteger;
import java.util.concurrent.*;

/**
 * 中断时实现取消的最合理方式
 * Created by frodo on 2015/1/15.
 */
public class PrimeProducer extends Thread {
    private final BlockingQueue<BigInteger> queue;

    public PrimeProducer(BlockingQueue<BigInteger> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            BigInteger p = BigInteger.ONE;
            // 中断时实现取消的最合理方式
            while (!Thread.currentThread().isInterrupted()) {
                queue.put(p = p.nextProbablePrime());
            }
        } catch (InterruptedException e) {
            // 允许程序退出
        }
    }

    static final ScheduledExecutorService cancelExec = Executors.newScheduledThreadPool(1);
    static final ExecutorService taskExec = Executors.newCachedThreadPool();

    /**
     * 解决了执行任务的线程拥有自己的执行策略，及时任务不响应中断，限时运行的方法仍然能返回到它的调用者。
     *
     * @param r
     * @param timeout
     * @param unit
     * @throws InterruptedException
     */
    public static void timeRun(final Runnable r, long timeout, TimeUnit unit) throws InterruptedException {
        class RethrowableTask implements Runnable {

            private volatile Throwable t;

            @Override
            public void run() {
                try {
                    r.run();
                } catch (Throwable t) {
                    this.t = t;
                }
            }

            void rethrow() {
                if (t != null) {
                    throw LaunderThrowable.launderThrowable(t);
                }
            }
        }

        RethrowableTask task = new RethrowableTask();
        final Thread taskThread = new Thread(task);
        taskThread.start();

        cancelExec.schedule(new Runnable() {
            @Override
            public void run() {
                taskThread.interrupt();
            }
        }, timeout, unit);

        taskThread.join(unit.toMillis(timeout));
        task.rethrow();
    }

    /**
     * 将任务提交给一个ExecutorService，并通过一个定时的Future.get来获得结果。
     * 如果get在返回时抛出一个TimeoutException，那么任务将通过它的Future来取消。
     * （同时，取消那些不在需要结果的任务）
     *
     * @param r
     * @param timeout
     * @param unit
     * @throws InterruptedException
     */
    public static void timeRun2(Runnable r, long timeout, TimeUnit unit) throws InterruptedException {
        Future<?> task = taskExec.submit(r);
        try {
            task.get(timeout, unit);
        } catch (TimeoutException e) {
            // 记下来的任务将被取消
        } catch (ExecutionException e) {
            // 如果在任务中抛出异常，那么重新抛出该异常
            throw LaunderThrowable.launderThrowable(e.getCause());
        } finally {
            // 如果任务已经结束，那么执行取消操作也不会带来任何影响
            task.cancel(true);// 如果任务正在运行，那么将被中断
        }
    }
}
