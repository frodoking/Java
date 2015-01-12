package cn.com.frodo.design.pattern.behavior.visitor.pattern;

public interface Visitor {
	public void visit(ConcreteElement1 el1);

	public void visit(ConcreteElement2 el2);
}
