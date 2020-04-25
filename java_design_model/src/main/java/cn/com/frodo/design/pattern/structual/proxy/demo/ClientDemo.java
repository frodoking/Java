package cn.com.frodo.design.pattern.structual.proxy.demo;

public class ClientDemo {

    public static void main(String[] args) {
        IGamePlayer player = new GamePlayer("李逍遥");

        GamePlayerProxy proxy = new GamePlayerProxy(player);
        proxy.killBoss();
        proxy.upGrade();
    }
}
