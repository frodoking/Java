package cn.com.frodo.design.pattern.creational.singletion.demo;

public class GlobalNum {
	private GlobalNum() {
	}

	private static GlobalNum _instance = new GlobalNum();
	private int num = 0;

	public static GlobalNum getInstance() {
		return _instance;
	}

	public synchronized int getNum() {
		return ++num;
	}
}
