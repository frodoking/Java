package cn.com.frodo.design.pattern.behavior.chainofresponsibility.pattern;

public class ConcreteHandler extends Handler {

    @Override
    public void handleRequest() {
        if (getSuccessor() != null) {
            System.out.println("请求给了" + getSuccessor());
            getSuccessor().handleRequest();
        } else {
            System.out.println("请求处理");
        }

    }
}
