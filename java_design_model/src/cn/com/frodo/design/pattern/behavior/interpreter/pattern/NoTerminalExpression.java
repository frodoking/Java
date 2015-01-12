package cn.com.frodo.design.pattern.behavior.interpreter.pattern;

public class NoTerminalExpression extends AbstractExpression {

	@Override
	public Object interpreter(Context context) {
		return null;
	}
}
