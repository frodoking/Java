package cn.com.frodo.design.pattern.structual.facade.pattern;

public class Facade {
    private ClassA a = new ClassA();
    private ClassB b = new ClassB();
    private ClassC c = new ClassC();

    public void methodA() {
        a.methodA();
    }

    public void methodB() {
        b.methodB();
    }

    public void methodC() {
        c.methodC();
    }
}
