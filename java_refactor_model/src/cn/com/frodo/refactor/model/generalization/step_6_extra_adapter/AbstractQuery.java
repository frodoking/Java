package cn.com.frodo.refactor.model.generalization.step_6_extra_adapter;

public abstract class AbstractQuery implements Query {

	protected abstract SDQuery createQuery();

	@Override
	public void login(String server, String user, String password) {
		//same
	}
	
	@Override
	public void doQuery() {
		createQuery();
	}
	
}
