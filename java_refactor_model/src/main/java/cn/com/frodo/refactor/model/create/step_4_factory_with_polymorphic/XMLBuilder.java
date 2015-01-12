package cn.com.frodo.refactor.model.create.step_4_factory_with_polymorphic;

public class XMLBuilder implements OutputBuilder {
	String str;

	public XMLBuilder(String str) {
		super();
		this.str = str;
	}

	@Override
	public void addBelow(String tag) {
		
	}

	@Override
	public void addAbove(String tag) {
		
	}

}
