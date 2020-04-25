package cn.com.frodo.design.pattern.behavior.visitor.pattern;

public class ConcreteVisitor implements Visitor {

    @Override
    public void visit(ConcreteElement1 el1) {
        el1.operation();
    }

    @Override
    public void visit(ConcreteElement2 el2) {
        el2.operation();
    }
}
