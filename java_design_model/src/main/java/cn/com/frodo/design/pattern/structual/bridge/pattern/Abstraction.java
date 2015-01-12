package cn.com.frodo.design.pattern.structual.bridge.pattern;

/**
 * 抽象化角色
 * 
 * @author XuWei4
 * 
 */
public abstract class Abstraction {
	private Implementor impl;

	Abstraction(Implementor impl) {
		this.impl = impl;
	}

	public void operation() {
		impl.operationImp();
	}

}
