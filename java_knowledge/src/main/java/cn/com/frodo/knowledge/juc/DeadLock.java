package cn.com.frodo.knowledge.juc;

/**
 * Created by frodoking on 2019/3/10 上午11:34.
 * Description:
 */
public class DeadLock {
    private static final String LOCK_A = "lock_a";
    private static final String LOCK_B = "lock_b";

    public void visit(String lock1, String lock2) {
        synchronized (lock1) {
            try {
                System.out.println("Go into lock1 = [" + lock1 + "]");
                Thread.sleep(1000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lock2) {
                try {
                    System.out.println("Go into lock2 = [" + lock2 + "]");
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void execute() {
        for (int i = 0; i < 10; i++) {
            final int position = i;
            new Thread(() -> {
                if (position % 2 == 0) {
                    visit(LOCK_A, LOCK_B);
                } else {
                    visit(LOCK_B, LOCK_A);
                }
            }).start();

        }
    }

    public static void main(String[] args) {
        new DeadLock().execute();
    }
}
