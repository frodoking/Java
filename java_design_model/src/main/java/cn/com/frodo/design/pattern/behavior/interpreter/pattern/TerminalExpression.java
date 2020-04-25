package cn.com.frodo.design.pattern.behavior.interpreter.pattern;

public class TerminalExpression extends AbstractExpression {
    TerminalExpression(AbstractExpression expression) {
    }

    @Override
    public Object interpreter(Context context) {
        return null;
    }
}
