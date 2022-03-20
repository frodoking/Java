package cn.com.frodo.knowledge.concurrent;

import cn.com.frodo.MockInterface;

/**
 * @author frodoking
 * @ClassName: SynchronizedTest
 * @date 2022/3/12
 */
public class SynchronizedTest implements MockInterface {
    @Override
    public synchronized void doTest() {
        System.out.println("-------------------------");
    }

    public void doTest2() {
        synchronized (MockInterface.class) {
            System.out.println("++++++++++++++++++++++++++");
        }
    }

    public synchronized void doTest3() {
            System.out.println("===========================");
    }
}
