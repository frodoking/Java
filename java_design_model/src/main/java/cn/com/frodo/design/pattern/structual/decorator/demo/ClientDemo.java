package cn.com.frodo.design.pattern.structual.decorator.demo;

public class ClientDemo {

    public static void main(String[] args) {
        Car car = new Benz();
        CarDecorator carDecorator = new ConcreteCarDecorator(car);
        carDecorator.show();
    }
}
