package cn.com.frodo.design.pattern.structual.bridge.pattern;

public class Client {

	public static void main(String[] args) {
		// 定义一个实现化角色
		Implementor imp = new ConcreteImplementor();
		// 定义一个抽象画角色
		Abstraction action = new RefinedAbstraction(imp);
		// 执行
		action.operation();
	}
}
