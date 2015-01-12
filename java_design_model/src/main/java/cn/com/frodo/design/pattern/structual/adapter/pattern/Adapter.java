package cn.com.frodo.design.pattern.structual.adapter.pattern;

public class Adapter extends Adaptee implements Taget {

	@Override
	public void request() {
		// 将Adaptee转化为Taget接口
		super.specificRequest();
	}
}
