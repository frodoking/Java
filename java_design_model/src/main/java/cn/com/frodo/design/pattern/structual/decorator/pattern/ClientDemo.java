package cn.com.frodo.design.pattern.structual.decorator.pattern;

public class ClientDemo {
    public static void main(String[] args) {
        Component component = new ConcreteComponent();
        component = new ConcreteDecorator(component);
        component.operation();
    }
}
