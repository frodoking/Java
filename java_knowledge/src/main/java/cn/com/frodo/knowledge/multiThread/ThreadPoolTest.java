package cn.com.frodo.knowledge.multiThread;

import cn.com.frodo.MockInterface;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Description 触发条件：线程数=maximumPoolSize 且 queue已满
 *
 * @author frodoking
 * @version [V1, 2019/8/2 14:13]
 */
public class ThreadPoolTest implements MockInterface {
    @SuppressWarnings("unchecked")
    @Override
    public void doTest() {
        ExecutorService executorService = new ThreadPoolExecutor(4, 14, 10L, TimeUnit.MICROSECONDS, new LinkedBlockingQueue(10));

        for (int i = 0; i < 25; i++) {
            final int index = i;
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(".....execute " + index + " " + System.currentTimeMillis());
                }
            });
        }

    }
}

