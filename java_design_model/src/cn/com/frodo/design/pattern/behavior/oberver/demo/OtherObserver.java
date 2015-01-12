package cn.com.frodo.design.pattern.behavior.oberver.demo;

public class OtherObserver implements ClickableObserver {

	@Override
	public void clicked(Clickable clickable) {
		System.out.println("执行其他操作");
	}
}
