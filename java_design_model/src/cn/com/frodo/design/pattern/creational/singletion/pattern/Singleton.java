package cn.com.frodo.design.pattern.creational.singletion.pattern;

/**
 * 单例模式,整个项目保证唯一一个类
 * 
 * @author XuWei4
 * 
 */
public class Singleton {
	private static Singleton _instance = new Singleton();

	//私有化构造函数，让外部无法直接实例化
	private Singleton() {

	};

	public static Singleton getInstance() {
		return _instance;
	}
}
