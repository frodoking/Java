package cn.com.frodo.design.pattern.structual.proxy.demo;

import java.util.Date;

public class GamePlayerProxy implements IGamePlayer {

	private IGamePlayer player = null;

	private void log() {
		System.out.println("打怪时间" + new Date().toString());
	}

	public GamePlayerProxy(IGamePlayer player) {
		this.player = player;
	}

	@Override
	public void killBoss() {
		log();
		player.killBoss();
	}

	@Override
	public void upGrade() {
		player.upGrade();
		count();
	}

	private void count() {
		System.out.println("升1级耗费50小时");
	}

}
