package cn.com.frodo.knowledge.aop;

/**
 * 业务类，需要代理的类。
 *
 * @author frodoking
 */
public class Business implements IBusiness, IBusiness2 {

    @Override
    public boolean doSomething2() {
        System.out.println("执行业务逻辑 2");
        return true;
    }

    @Override
    public boolean doSomething() {
        System.out.println("执行业务逻辑 1");
        return true;
    }

}
