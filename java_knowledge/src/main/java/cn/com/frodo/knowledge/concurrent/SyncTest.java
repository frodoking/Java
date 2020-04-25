package cn.com.frodo.knowledge.concurrent;

import cn.com.frodo.MockInterface;

/**
 * Description TODO
 *
 * @author frodoking
 * @version [V1, 2019/8/1 15:39]
 */
public class SyncTest implements MockInterface {
    static final int SIZE = 10;

    static volatile int size = 0;

    static Object LOCK = new Object();

    public static class Producer {
        void produce() {
            synchronized (LOCK) {
                if (size == SIZE) {
                    try {
                        LOCK.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                size++;
                System.out.println("produce one, current size: " + size);
                LOCK.notify();
            }
        }
    }

    public static class Consumer {
        void consume() {
            synchronized (LOCK) {
                if (size == 0) {
                    try {
                        LOCK.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                size--;
                System.out.println("consume one, current size: " + size);
                LOCK.notify();
            }
        }
    }

    @Override
    public void doTest() {
        final Producer producer = new Producer();
        final Consumer consumer = new Consumer();
        for (int i = 0; i < SIZE; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    producer.produce();
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    consumer.consume();
                    try {
                        Thread.sleep(90);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}
