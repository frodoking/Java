package cn.com.frodo.design.pattern.behavior.interpreter.demo;

public class ClientDemo {
    public static void main(String[] args) {
        Variables v = new Variables();

        Variable x = new Variable();
        Variable y = new Variable();

        v.put(x, 10);
        v.put(y, 30);

        ArithmeticExpression e = new Plus(x, y);
        System.out.println(e.interpret(v));
    }
}
