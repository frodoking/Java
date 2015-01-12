package cn.com.frodo.design.pattern.behavior.memento.pattern;

/**
 * 备忘录模式： 在不破坏封装性的前提下，捕获一个对象的内部状态，
 * 并在该对象之外保存这个状态。这样，以后就可以将该对象恢复到原先保存的状态
 * 
 * @author XuWei4
 * 
 */
public class Client {

	public static void main(String[] args) {
		Originator org = new Originator();

		Caretaker caretaker = new Caretaker();

		caretaker.setMemento(org.createMemento());
		org.restoreMemento(caretaker.getMemento());
	}
}
