package cn.com.frodo.design.pattern.behavior.oberver.demo;

public class ClientDemo {
    public static void main(String args[]) {
        Button btn = new Button();
        btn.color = "红色";
        btn.x = 0;
        btn.y = 0;

        btn.addClickableObserver(new ChangeColorObserver());
        btn.addClickableObserver(new ChangeCoordinateObserver());
        btn.addClickableObserver(new OtherObserver());

        btn.click();
        System.out.println(btn);
    }
}
