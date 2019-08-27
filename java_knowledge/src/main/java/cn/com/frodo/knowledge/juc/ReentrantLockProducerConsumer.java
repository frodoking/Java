package cn.com.frodo.knowledge.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by frodoking on 2019/3/10 上午11:27.
 * Description:
 */
public class ReentrantLockProducerConsumer implements ProducerConsumer {

    public static int count = 0;
    public static final int FULL = 10;

    private ReentrantLock lock = new ReentrantLock();
    private Condition notEmpty = lock.newCondition();
    private Condition notFull = lock.newCondition();

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
                    lock.lock();
                    while (count == FULL) {
                        try {
                            notFull.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    count++;
                    System.out.println(Thread.currentThread().getName() + "生产：" + count);
                    notEmpty.signal();
                } finally {
                    lock.unlock();
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
                    lock.lock();
                    while (count == 0) {
                        try {
                            notEmpty.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    count--;
                    System.out.println(Thread.currentThread().getName() + "消费：" + count);
                    notFull.signal();
                } finally {
                    lock.unlock();
                }
            }
        }).start();
    }
}
