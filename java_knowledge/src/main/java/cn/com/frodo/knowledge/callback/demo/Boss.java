package cn.com.frodo.knowledge.callback.demo;

public class Boss {

	private String name;

	public Boss(String name) {
		this.name = name;
	}

	public void getStaffEvent(Staff staff, Event event) {
		System.out.println("the msg what the boss received is--" + staff.getName() + ":" + event.happendEvent());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
