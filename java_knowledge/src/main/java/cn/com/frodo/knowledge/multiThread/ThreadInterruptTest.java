package cn.com.frodo.knowledge.multiThread;

/**
 * @author frodoking
 * @ClassName: ThreadInterruptTest
 * @date 2020/9/29
 */
public class ThreadInterruptTest {

    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (; ; ) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(System.currentTimeMillis());
                }
            }
        });
        thread.start();
        try {
            Thread.sleep(5000);
            thread.stop();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
