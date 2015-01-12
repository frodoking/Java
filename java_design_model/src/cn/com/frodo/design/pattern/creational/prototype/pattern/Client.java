package cn.com.frodo.design.pattern.creational.prototype.pattern;

public class Client {
	public void operation(Prototype example) {
		Prototype p = example.clone();
	}
}
