package cn.com.frodo.design.pattern.behavior.oberver.demo;

public class ChangeColorObserver implements ClickableObserver {

    @Override
    public void clicked(Clickable clickable) {
        Button b = (Button) clickable;
        b.color = "红色";
    }
}
