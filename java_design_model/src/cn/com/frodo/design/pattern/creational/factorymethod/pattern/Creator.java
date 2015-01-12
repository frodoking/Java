package cn.com.frodo.design.pattern.creational.factorymethod.pattern;

public interface Creator {
	/**
	 * 创建一个产品对象，参数可以自己设置
	 * 
	 * @param c
	 * @return
	 */
	public <T extends Product> T factory(Class<T> c);
}
