package cn.com.frodo.knowledge.aop;

import java.lang.reflect.Proxy;

/**
 * AOP,面向切面编程，可以通过预编译方式和运行期动态代理实现在不修改源代码的情况下给程序动态统一添加功能的一种技术。 Aspect Oriented
 * Programming(AOP),是目前软件开发中的一个热点，也是Spring框架中的一个重要内容。利用AOP可以对业务逻辑的各个部分进行隔离，
 * 从而使得业务逻辑各部分之间的耦合度降低，提高程序的可重用性，同时提高了开发的效率。
 *
 * @author frodoking
 */
public class AOPTest {

    public static void main(String[] args) {
        // 需要代理的接口，被代理类实现的多个接口都必须在这里定义
        Class[] proxyInterface = new Class[]{IBusiness.class, IBusiness2.class};
        // 构建AOP的Advice，这里需要传入业务类的实例
        LogInvocationHandler handler = new LogInvocationHandler(new Business());
        // 生成代理类的字节码加载器
        ClassLoader classLoader = AOPTest.class.getClassLoader();
        // 织入器，织入代码并生成代理类
        Object obj = Proxy.newProxyInstance(classLoader, proxyInterface, handler);
        System.out.println("--------start-----------------");
        IBusiness proxyBusiness = (IBusiness) obj;
        // 使用代理类的实例来调用方法。
        proxyBusiness.doSomething();
        System.out.println("----------doSomething---------------");
        IBusiness2 proxyBusiness2 = (IBusiness2) obj;
        proxyBusiness2.doSomething2();
        System.out.println("----------doSomething2---------------");

    }
}
