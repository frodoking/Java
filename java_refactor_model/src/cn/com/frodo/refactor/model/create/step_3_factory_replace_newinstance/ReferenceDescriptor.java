package cn.com.frodo.refactor.model.create.step_3_factory_replace_newinstance;

public class ReferenceDescriptor extends AttributeDescriptor {
	String flag;
	Class clazz;
	Class clazz2;
	Class clazz3;

	public ReferenceDescriptor(String flag, Class clazz, Class clazz2, Class clazz3) {
		super();
		this.flag = flag;
		this.clazz = clazz;
		this.clazz2 = clazz2;
		this.clazz3 = clazz3;
	}

}
