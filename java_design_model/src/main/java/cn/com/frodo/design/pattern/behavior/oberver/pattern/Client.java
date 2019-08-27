package cn.com.frodo.design.pattern.behavior.oberver.pattern;

/**
 * 观察者模式：定义对象间一种一对多的依赖关系，使得每当一个对象改变状态，
 * 则所有依赖于它的 对象都会得到通知并被自动更新
 * 
 * @author frodoking
 * 
 */
public class Client {
	public static void main(String args[]) {
		Subject subject = new ConcreteSubject();

		Observer observer = new ConcreteObserver();

		subject.attach(observer);
		subject.notifyObserver();
	}
}
