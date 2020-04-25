package cn.com.frodo.knowledge.multiThread;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by frodo on 2017/8/29.
 */
public class LimitRateThreadPool {
    public static void main(String[] args) {
        final ExecutorService executorService = new ThreadPoolExecutor(3, 10, 5, TimeUnit.MINUTES,
                new LinkedBlockingDeque<Runnable>(10), new ThreadPoolExecutor.CallerRunsPolicy());
        AtomicInteger integer = new AtomicInteger(0);
        for (int i = 0; i < 100; i++) {
            final int position = i;
            System.out.println("semaphore.acquire --- index: " + position);
            executorService.submit(() -> {
                System.out.println("incrementAndGet = [" + integer.incrementAndGet() + "]");
                try {
                    System.out.println("executorService.exe --- index: " + position);
                    int time = new Random().nextInt(5) + 5;
                    Thread.sleep(time * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("decrementAndGet = [" + integer.decrementAndGet() + "]");
            });
        }
        System.out.println("Get = [" + integer.get() + "]");
    }
}
