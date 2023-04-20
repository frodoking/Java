package cn.com.frodo.design.pattern.creational.singletion.pattern;

/**
 * 单例模式,整个项目保证唯一一个类
 *
 * @author frodoking
 */
public class Singleton {
    private static volatile Singleton _instance;

    //私有化构造函数，让外部无法直接实例化
    private Singleton() {
    }

    public static Singleton getInstance() {
        if (_instance == null) {
            synchronized (Singleton.class) {
                if (_instance == null) {
                    _instance = new Singleton();
                }
            }
        }
        return _instance;
    }
}
