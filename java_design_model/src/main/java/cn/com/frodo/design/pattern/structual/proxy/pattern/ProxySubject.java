package cn.com.frodo.design.pattern.structual.proxy.pattern;

public class ProxySubject implements Subject {

    private Subject subject;

    public ProxySubject(Subject subject) {
        this.subject = subject;
    }

    @Override
    public void request() {
        this.beforeRequest();
        subject.request();
        this.afterRequest();
    }

    /**
     * 预处理
     */
    private void beforeRequest() {

    }

    /**
     * 后处理
     */
    private void afterRequest() {

    }

}
