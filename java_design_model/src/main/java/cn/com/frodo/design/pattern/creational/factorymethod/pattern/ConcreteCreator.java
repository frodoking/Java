package cn.com.frodo.design.pattern.creational.factorymethod.pattern;

/**
 * 具体工厂
 * 
 * @author frodoking
 * 
 */
public class ConcreteCreator implements Creator {

	@SuppressWarnings("unchecked")
	@Override
	public <T extends Product> T factory(Class<T> c) {
		Product product = null;
		try {
			product = (Product) Class.forName(c.getName()).newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (T) product;
	}
}
