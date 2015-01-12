package cn.com.frodo.refactor.model.generalization.step_5_adapter;

public interface XMLNode {
	void add(XMLNode childNode);

	void addAttribute(String attribute, String value);

	void addValue(String value);
}
