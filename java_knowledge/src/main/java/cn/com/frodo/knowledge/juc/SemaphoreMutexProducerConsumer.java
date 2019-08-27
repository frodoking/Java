package cn.com.frodo.knowledge.juc;

import sun.awt.Mutex;

import java.util.concurrent.Semaphore;

/**
 * Created by frodoking on 2019/3/10 上午11:45.
 * Description:
 */
public class SemaphoreMutexProducerConsumer implements ProducerConsumer {

    public static final Semaphore NOT_EMPTY = new Semaphore(0);
    public static final Semaphore NOT_FULL = new Semaphore(10);
    public static final Mutex lock = new Mutex();
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
                    lock.lock();
                    count++;
                    System.out.println(Thread.currentThread().getName() + "生产：" + count);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
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
                    lock.lock();
                    count--;
                    System.out.println(Thread.currentThread().getName() + "消费：" + count);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                    NOT_FULL.release();
                }
            }
        }).start();
    }
}
