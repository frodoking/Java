package cn.com.frodo.knowledge.callback.demo;

/**
 * 一个典型的回调案例：公司员工工作，工作完成后主管要求员工汇报工作完成情况。
 * 
 * @author frodoking 事件接口：
 */
public interface Event {
	/**
	 * 返回发生事件信息
	 * 
	 * @return 事件信息
	 */
	public String happendEvent();
}
