package cn.com.frodo.refactor.model.generalization.step_5_adapter;

public class TagNode implements XMLNode {
	
	String name;
	

	public TagNode(String name) {
		this.name = name;
	}

	@Override
	public void add(XMLNode childNode) {
		
	}

	@Override
	public void addAttribute(String attribute, String value) {
		
	}

	@Override
	public void addValue(String value) {
		
	}

}
