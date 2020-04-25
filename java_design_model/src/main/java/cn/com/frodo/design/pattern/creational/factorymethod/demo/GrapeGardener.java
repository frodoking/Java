package cn.com.frodo.design.pattern.creational.factorymethod.demo;

public class GrapeGardener implements FruitGardener {

    @Override
    public Fruit factory() {
        return new Grape();
    }
}
