package cn.com.frodo.design.pattern.structual.adapter.pattern;

public class Client {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Taget taget = new Adapter();
		taget.request();
	}
}
