package cn.com.frodo.knowledge.callback.demo;

public class Staff {
    private Event event; // 事件
    private String name; // 员工姓名
    private Boss boss; // 员工所属主管

    /**
     * 员工构造器
     *
     * @param name 员工姓名
     * @param boss 传入Boss对象，便于回调反馈工作状况
     */

    public Staff(String name, Boss boss) {
        this.name = name;
        this.boss = boss;
    }

    public void doWork() {
        System.out.println(name + " is doing working...");
        // do somtething.....
        for (int i = 0; i < 10; i++) {
            System.out.println("sheep" + i);
        }
        System.out.println(name + " was finished work!");
        // tell the boss what has happend,这里就是boss的回调方法
        boss.getStaffEvent(this, event);
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boss getBoss() {
        return boss;
    }

    public void setBoss(Boss boss) {
        this.boss = boss;
    }
}
