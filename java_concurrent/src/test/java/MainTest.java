import cn.com.frodo.concurrent.chapter_5.TestHarness;

/**
 * Created by frodoking on 2015/1/14.
 */
public class MainTest {
    public static void main(String[] args){
        TestHarness testHarness = new TestHarness();
        try {

            System.out.println(System.nanoTime());
            long time = testHarness.timeTasks(10, new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + "  start");
                }
            });
            System.out.println(System.nanoTime());
            System.out.println("total : "+ time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
