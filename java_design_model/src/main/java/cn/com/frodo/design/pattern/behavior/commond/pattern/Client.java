package cn.com.frodo.design.pattern.behavior.commond.pattern;

/**
 * 命令模式：将一个请求封装成一个对象，从而让你使用不同的请求把客户端参数化， 
 * 对请求排队或者记录请求日志，可以提供命令得撤销和恢复功能
 * 
 * @author frodoking
 * 
 */
public class Client {
	public static void main(String args[]) {
		Invoker invoker = new Invoker();

		Receiver receiver = new Receiver();
		Commond commond = new ConcreteCommond(receiver);

		invoker.setCommond(commond);
		invoker.action();
	}
}
