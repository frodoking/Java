package cn.com.frodo.knowledge.concurrent;

import cn.com.frodo.MockInterface;
import cn.com.frodo.knowledge.encrypt.UnifiedManager;

import java.util.concurrent.locks.ReentrantLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author frodoking
 * @ClassName: ReentrantLockThreadTest
 * @date 2022/3/4
 */
public class ReentrantLockThreadTest implements MockInterface {

    private static final Logger log = LoggerFactory.getLogger(UnifiedManager.class);

    @Override
    public void doTest() {
        ReentrantLock reentrantLock = new ReentrantLock();

        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            final int j = i;
           Thread t= new Thread(new Runnable() {
                @Override
                public void run() {
                    reentrantLock.lock();
                    try {
                        Thread.sleep(1000 * j);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    log.info("<< {} : {}",Thread.currentThread().getName(), Thread.currentThread().getState());
                    reentrantLock.unlock();
                }
            }, "Thread-"+i);
           threads[i] = t;
           t.start();
        }
        for (;;) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < 10; i++) {
                log.info(">> {} : {}",threads[i].getName(), threads[i].getState());
            }
        }
    }
}
