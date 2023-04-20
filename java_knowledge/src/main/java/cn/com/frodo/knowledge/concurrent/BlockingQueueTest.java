package cn.com.frodo.knowledge.concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class BlockingQueueTest {

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Long> queue = new ArrayBlockingQueue<Long>(10);

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                for(;;) {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    queue.offer(System.currentTimeMillis());
                }
            }
        });
        t.start();

        Long a;
        while ((a = queue.take()) != null) {
            System.out.println(a);
        }

        t.join();
    }
}
