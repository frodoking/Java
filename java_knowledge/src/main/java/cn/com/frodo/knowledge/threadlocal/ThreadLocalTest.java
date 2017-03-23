package cn.com.frodo.knowledge.threadlocal;

/**
 * Created by xuwei19 on 2016/6/17.
 */
public class ThreadLocalTest {

    public static void main(String[] args){
        final ThreadLocal<Integer> threadLocal = new ThreadLocal<>();
        threadLocal.set(-1);
        for (int i = 0; i < 10; i++) {
            final int position = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Thread t= Thread.currentThread();
                    System.out.println("Thread -- " + t.getName() + ", ThreadLocal: get " + threadLocal.get());
                    System.out.println("\n");
                }
            }).start();
        }
    }
}
