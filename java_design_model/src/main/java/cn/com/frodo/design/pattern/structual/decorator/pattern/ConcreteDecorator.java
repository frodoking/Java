package cn.com.frodo.design.pattern.structual.decorator.pattern;

public class ConcreteDecorator extends Decorator {

    public ConcreteDecorator(Component component) {
        super(component);
    }

    private void method() {
        System.out.println("修饰");
    }

    @Override
    public void operation() {
        this.method();
        super.operation();
    }
}
