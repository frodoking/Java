package cn.com.frodo.design.pattern.creational.factorymethod.pattern;

public class FactoryMethodDemo {
	public static void main(String[] args) {
		Creator creator = new ConcreteCreator();
		Product product = creator.factory(ConcreteProduct.class);
		/**
		 * 下面继续业务逻辑处理
		 */
		product.method1();
		product.method2();
	}
}
