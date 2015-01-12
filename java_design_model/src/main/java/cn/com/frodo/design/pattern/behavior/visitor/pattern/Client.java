package cn.com.frodo.design.pattern.behavior.visitor.pattern;
/**
 * 访问者模式定义 ：封装一些作用于某种数据结构的各种元素的操作，它可以在不改变数据结构的前提下定义作用于这些元素的新的操作
 * @author XuWei4
 *
 */
public class Client {

	public static void main(String[] args) {
		ObjectStructure obj = new ObjectStructure();
		
		obj.createElements();
		
		Visitor vi = new ConcreteVisitor();
		obj.action(vi);
	}
}
