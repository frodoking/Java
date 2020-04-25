package cn.com.frodo.design.pattern.structual.flyweight.demo;

public class ChesspieceFlyweight implements Chesspiece {
    private String color;

    ChesspieceFlyweight(String color) {
        this.color = color;
    }

    @Override
    public void put(int x, int y) {
        System.out.println("在" + x + "，  " + y + "位置放一个" + color + "子");
    }
}
