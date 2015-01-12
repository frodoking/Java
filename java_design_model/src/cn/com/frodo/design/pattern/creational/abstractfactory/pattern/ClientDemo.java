package cn.com.frodo.design.pattern.creational.abstractfactory.pattern;

public class ClientDemo {
	public static void main(String[] args) {
		AbstractFactory factory1 = new ConcreteFactory1();
		ProductA a1 = factory1.factoryA();
		ProductB b1 = factory1.factoryB();

		a1.method1();
		a1.method2();

		b1.method1();
		b1.method2();

		AbstractFactory factory2 = new ConcreteFactory1();
		ProductA a2 = factory2.factoryA();
		ProductB b3 = factory2.factoryB();

		a2.method1();
		a2.method2();

		b3.method1();
		b3.method2();
	}
}
