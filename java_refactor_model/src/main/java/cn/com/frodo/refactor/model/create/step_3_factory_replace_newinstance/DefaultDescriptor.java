package cn.com.frodo.refactor.model.create.step_3_factory_replace_newinstance;

/**
 * 被用来表示Integer和 Date类型映射
 * 
 * @author XuWei4
 * 
 */
public class DefaultDescriptor extends AttributeDescriptor {
	String flag;
	Class clazz;
	Class clazz2;

	protected DefaultDescriptor(String flag, Class clazz, Class clazz2) {
		super();
		this.flag = flag;
		this.clazz = clazz;
		this.clazz2 = clazz2;
	}

}
