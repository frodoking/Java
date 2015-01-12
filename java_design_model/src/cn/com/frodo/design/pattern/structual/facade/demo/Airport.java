package cn.com.frodo.design.pattern.structual.facade.demo;

public class Airport {
	public void bookTicket(String from, String to) {
		System.out.println("订了一桌" + from + "到" + to + "的飞机");
	}
}
