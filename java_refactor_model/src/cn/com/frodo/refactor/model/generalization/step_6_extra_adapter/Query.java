package cn.com.frodo.refactor.model.generalization.step_6_extra_adapter;

public interface Query {
	void login(String server, String user, String password);

	void doQuery();
}
