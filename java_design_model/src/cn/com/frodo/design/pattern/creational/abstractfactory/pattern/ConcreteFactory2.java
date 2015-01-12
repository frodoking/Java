package cn.com.frodo.design.pattern.creational.abstractfactory.pattern;

public class ConcreteFactory2 implements AbstractFactory {

	@Override
	public ProductA factoryA() {
		return new ProductA2();
	}

	@Override
	public ProductB factoryB() {
		return new ProductB3();
	}

}
