package cn.com.frodo.design.pattern.structual.bridge.pattern;

public class RefinedAbstraction extends Abstraction {

    RefinedAbstraction(Implementor impl) {
        super(impl);
    }

    @Override
    public void operation() {
        // 修正父类的方法
    }
}
