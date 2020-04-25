package cn.com.frodo.design.pattern.behavior.visitor.pattern;

public class ConcreteElement2 extends Element {

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }

    public void operation() {
        System.out.println("访问2");
    }
}
