package cn.com.frodo.design.pattern.structual.bridge.demo;

public abstract class AbstractShape {
    Color color;

    AbstractShape(Color color) {
        this.color = color;
    }

    public abstract void onDraw();
}
