package cn.com.frodo.design.pattern.behavior.chainofresponsibility.pattern;

public abstract class Handler {
	private Handler successor;

	public abstract void handleRequest();

	public Handler getSuccessor() {
		return successor;
	}

	public void setSuccessor(Handler successor) {
		this.successor = successor;
	}
}
