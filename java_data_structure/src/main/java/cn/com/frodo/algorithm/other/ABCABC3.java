package cn.com.frodo.algorithm.other;

import cn.com.frodo.algorithm.IAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Semaphore;

/**
 * @author frodoking
 * @ClassName: ABCABC3
 * @date 2022/3/6
 */
public class ABCABC3 implements IAlgorithm {

    private static final Logger log = LoggerFactory.getLogger(ABCABC3.class);

    private final int times;

    public ABCABC3(int times) {
        this.times = times;
    }

    @Override
    public void exec() {
        final Semaphore semaphoreA = new Semaphore(1);
        final Semaphore semaphoreB = new Semaphore(0);
        final Semaphore semaphoreC = new Semaphore(0);
        new Thread(() -> print("A", semaphoreA, semaphoreB)).start();
        new Thread(() -> print("B", semaphoreB, semaphoreC)).start();
        new Thread(() -> print("C", semaphoreC, semaphoreA)).start();
    }

    private void print(String s, Semaphore curr, Semaphore next) {
        for (int i = 0; i < times; ) {
            try {
                curr.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i++;

            log.info("{} >> {}", Thread.currentThread().getName(), s);
            next.release();
        }
    }


}
