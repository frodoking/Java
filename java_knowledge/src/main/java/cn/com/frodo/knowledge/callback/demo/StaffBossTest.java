package cn.com.frodo.knowledge.callback.demo;

/**
 * 一个典型的回调案例：公司员工工作，工作完成后主管要求员工汇报工作完成情况。
 * 
 * @author XuWei4
 * 
 */
public class StaffBossTest {

	public static void main(String[] args) {
		//初始化主管和员工
		Boss boss = new Boss("boss");
		Staff a = new Staff("a", boss);
		Staff b = new Staff("b", boss);
		
		//主管发放了两个新任务 
		Event evt1 = new EventA();
		Event evt2 = new EventB();
		
		
		//员工接受任务开始干活 
		a.setEvent(evt1);
		b.setEvent(evt2);
		
		
		 //员工干晚活，及时向主管反馈工作情况 
		a.doWork();
		b.doWork();
	}
}
