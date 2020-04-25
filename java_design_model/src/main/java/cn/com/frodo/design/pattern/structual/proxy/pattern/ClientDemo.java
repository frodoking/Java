package cn.com.frodo.design.pattern.structual.proxy.pattern;

public class ClientDemo {
    public static void main(String[] args) {
        Subject subject = new RealSubject();

        // 代理商装配所有的真实对象，然后统一做请求处理
        ProxySubject proxy = new ProxySubject(subject);
        proxy.request();
    }
}
