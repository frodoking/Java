package cn.com.frodo.design.pattern.creational.singletion.demo;

public class SingleDemo {
	public static void main(String[] args) {
		NumThread thread1 = new NumThread("thread A");
		NumThread thread2 = new NumThread("thread B");
		thread1.start();
		thread2.start();
	}
}

class NumThread extends Thread {

	private String threadName;

	public NumThread(String threadName) {
		this.threadName = threadName;
	}

	@Override
	public void run() {
		GlobalNum globalNum = GlobalNum.getInstance();
		for (int i = 0; i < 5; i++) {
			System.out.println(threadName + "--------" + globalNum.getNum()
					+ "次访问!");
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
