package cn.com.frodo.design.pattern.behavior.chainofresponsibility.pattern;

/**
 * 使多个对象都有机会处理请求，从而避免了请求的发送者和接受者之间的耦合关系
 * <p>
 * 责任链 重点在于“链”上，由一条链去处理相似的请求，在链中决定谁来处理这个请求
 *
 * @author frodoking
 */
public class Client {

    public static void main(String[] args) {
        Handler h1 = new ConcreteHandler();
        Handler h2 = new ConcreteHandler();
        h1.setSuccessor(h2);
        h1.handleRequest();
    }
}
