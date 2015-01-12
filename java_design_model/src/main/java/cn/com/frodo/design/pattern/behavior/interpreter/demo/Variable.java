package cn.com.frodo.design.pattern.behavior.interpreter.demo;

public class Variable implements ArithmeticExpression {

	@Override
	public int interpret(Variables variables) {
		return variables.getVariable(this);
	}
}
