package cn.com.frodo.design.pattern.behavior.commond.pattern;

public class Invoker {
	private Commond commond;

	public void setCommond(Commond commond) {
		this.commond = commond;
	}

	public void action() {
		commond.execute();
	}
}
