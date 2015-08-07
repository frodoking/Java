package cn.com.frodo.performance.optimization;

import org.junit.Test;

/**
 * Created by xuwei19 on 2015/7/19.
 */
public class VolatileTest {
    volatile boolean isExist;

    public void tryExit() {
        if (isExist == !isExist) {
            System.exit(0);
        }
    }

    public void swapValue() {
        isExist = !isExist;
    }

    @Test
    public void test() throws InterruptedException {
        final VolatileTest volObj = new VolatileTest();
        new Thread() {
            @Override
            public void run() {
                System.out.println("main thread start");
                while (true) {
                    // 不停尝试是否可以退出
                    volObj.tryExit();
                }
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                System.out.println("swap thread start");
                while (true) {
                    // 不停修改isExist 值
                    volObj.swapValue();
                }
            }
        }.start();

        Thread.sleep(1000);
    }
}
