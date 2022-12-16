package cn.com.frodo.knowledge.multiThread;

import cn.com.frodo.MockInterface;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

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
        ExecutorService executorService = new ThreadPoolExecutor(10, 10, 2,
                TimeUnit.SECONDS, new ArrayBlockingQueue<>(1));

        AtomicInteger counter = new AtomicInteger(0);
        for (int i = 0; i < 11; i++) {
            final int index = i;
            try {
                executorService.execute(new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            TimeUnit.SECONDS.sleep(3);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println(index + ".....execute " + counter.incrementAndGet());
                    }
                }));
            } catch (Exception e) {
                System.out.println(index + ".....rejected  ");
            }
        }
        executorService.shutdown();
    }
}

