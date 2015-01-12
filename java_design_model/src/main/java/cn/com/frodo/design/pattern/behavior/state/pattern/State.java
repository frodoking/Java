package cn.com.frodo.design.pattern.behavior.state.pattern;

public abstract class State {
	protected Context context;

	public void setContext(Context context) {
		this.context = context;
	}

	public abstract void handle();
}
