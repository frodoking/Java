package cn.com.frodo.design.pattern.structual.proxy.demo;

public class GamePlayer implements IGamePlayer {
    private String playerName;
    private static int level = 1;

    public GamePlayer(String playerName) {
        this.playerName = playerName;
    }

    @Override
    public void killBoss() {
        System.out.println(this.playerName + "在打怪!!!");
    }

    @Override
    public void upGrade() {
        level++;
        System.out.println(this.playerName + "升级到   " + level + "  !!!");
    }

}
