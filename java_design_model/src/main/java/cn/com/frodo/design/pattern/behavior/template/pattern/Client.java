package cn.com.frodo.design.pattern.behavior.template.pattern;

/**
 * 模板方法使用场景：多个子类有公共方法，并且逻辑基本相同；把重要的复杂的，核心的算法设计成模板，周边细节由子类实现，重构经常使用的
 * 
 * @author frodoking
 * 
 */
public class Client {

	public static void main(String[] args) {
		AbstractClass ac = new ConcreteClass();
		ac.operation();
		ac.templateMethod();
	}
}
