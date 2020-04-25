package cn.com.frodo.design.pattern.behavior.oberver.demo;

public class ChangeCoordinateObserver implements ClickableObserver {

    @Override
    public void clicked(Clickable clickable) {
        Button b = (Button) clickable;
        b.x = 100;
        b.y = 90;
    }
}
