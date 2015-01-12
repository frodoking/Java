package cn.com.frodo.design.pattern.structual.adapter.demo;

public class Food extends ShuiJiao implements Hundun {

	@Override
	public void makeHundun() {
		super.makeShuiJiao();
		System.out.println("用水饺的馅儿~~~");
	}
}
