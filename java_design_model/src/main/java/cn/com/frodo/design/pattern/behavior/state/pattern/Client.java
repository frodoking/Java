package cn.com.frodo.design.pattern.behavior.state.pattern;

public class Client {
    public static void main(String[] args) {
        Context context = new Context();

        context.handle1();
        context.handle2();
    }
}
