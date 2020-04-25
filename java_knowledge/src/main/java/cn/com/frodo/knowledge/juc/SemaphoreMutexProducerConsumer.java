package cn.com.frodo.knowledge.juc;

import java.util.concurrent.Semaphore;

/**
 * Created by frodoking on 2019/3/10 上午11:45.
 * Description:
 */
public class SemaphoreMutexProducerConsumer implements ProducerConsumer {

    public static final Semaphore NOT_EMPTY = new Semaphore(0);
    public static final Semaphore NOT_FULL = new Semaphore(10);
    // 互斥量，控制buffer的互斥访问
    private Semaphore mutex = new Semaphore(1);
    public static int count = 0;

    @Override
    public void produce() {
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                try {
                    NOT_FULL.acquire();
                    mutex.acquire();
                    count++;
                    System.out.println(Thread.currentThread().getName() + "生产：" + count);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    mutex.release();
                    NOT_EMPTY.release();
                }
            }
        }).start();
    }

    @Override
    public void consume() {
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                try {
                    NOT_EMPTY.acquire();
                    mutex.acquire();
                    count--;
                    System.out.println(Thread.currentThread().getName() + "消费：" + count);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    mutex.release();
                    NOT_FULL.release();
                }
            }
        }).start();
    }
}
