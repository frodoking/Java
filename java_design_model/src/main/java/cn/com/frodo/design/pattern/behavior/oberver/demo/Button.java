package cn.com.frodo.design.pattern.behavior.oberver.demo;

import java.util.ArrayList;
import java.util.List;

public class Button implements Clickable {
    List<ClickableObserver> observers = new ArrayList<ClickableObserver>();

    String color;
    int x, y;

    @Override
    public void click() {
        System.out.println("按钮被单击");
        for (int i = 0; i < observers.size(); i++) {
            observers.get(i).clicked(this);
        }
    }

    @Override
    public void addClickableObserver(ClickableObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeClickableObserver(ClickableObserver observer) {
        observers.remove(observer);
    }

    @Override
    public String toString() {
        return "按钮颜色: " + color + ", 坐标： " + x + ", " + y;
    }
}
