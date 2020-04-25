package cn.com.frodo.design.pattern.creational.factorymethod.demo;

public class ClientDemo {
    public static void main(String[] args) {
        FruitGardener appleGardener = new AppleGardener();
        Fruit apple = appleGardener.factory();

        apple.plant();
        apple.grow();
        apple.harvest();

        FruitGardener grapeGardener = new GrapeGardener();
        Fruit grape = grapeGardener.factory();

        grape.plant();
        grape.grow();
        grape.harvest();
    }
}
