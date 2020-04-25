package cn.com.frodo.design.pattern.creational.factorymethod.demo;

public class AppleGardener implements FruitGardener {

    @Override
    public Fruit factory() {
        return new Apple();
    }
}
