package cn.com.frodo.algorithm.other;

import cn.com.frodo.algorithm.AlgorithmPoint;
import cn.com.frodo.algorithm.IAlgorithm;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 设计一个多线程打印程序，第 i 个线程只打印 i-1 数字，比如第 1 个线程打印数字 0，第 2 个线程只打印数字 1，依次类推。
 * 任意给定一个数字序列，比如 3382019835830，能够使用该程序打印出来。
 */
@AlgorithmPoint(
        tag = AlgorithmPoint.Tag.interview,
        difficulty = AlgorithmPoint.Difficulty.hard,
        category = AlgorithmPoint.Category.thread)
public class ThreadExplainPrint implements IAlgorithm {

    @Override
    public void exec() {
        AtomicInteger counter = new AtomicInteger(0);
        String str = "3382019835830";

        Semaphore[] semaphores = new Semaphore[10];
        for (int i = 0; i < 10; i++) {
            semaphores[i] = new Semaphore(0);
        }

        for (int i = 0; i < 10; i++) {
            new Thread(createTask(str, counter, i, semaphores), "" + (i + 1)).start();
        }

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        semaphores[str.charAt(0) - '0'].release();
    }

    private Runnable createTask(String str, AtomicInteger counter, int num, Semaphore[] semaphores) {
        return () -> {
            while (counter.get() < str.length()) {
                try {
                    semaphores[num].acquire();
                    System.out.println(Thread.currentThread().getName() + "------------" + num + "------------" + str.charAt(counter.get()));
                    counter.incrementAndGet();
                    if (counter.get() < str.length()) {
                        semaphores[str.charAt(counter.get()) - '0'].release();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
    }
}
