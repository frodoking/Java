package cn.com.frodo.knowledge.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 打印日志的切面(一个代理类)
 *
 * @author frodoking
 */
public class LogInvocationHandler implements InvocationHandler {

    private Object target; // 目标对象

    LogInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 执行原有逻辑
        Object rev = method.invoke(target, args);
        // 执行织入的日志，你可以控制哪些方法执行切入逻辑
        if (method.getName().equals("doSomething")) {
            System.out.println("记录日志 1");
        } else if (method.getName().equals("doSomething2")) {
            System.out.println("记录日志 2");
        }
        return rev;
    }
}
