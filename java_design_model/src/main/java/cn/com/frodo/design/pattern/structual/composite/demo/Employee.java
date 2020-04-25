package cn.com.frodo.design.pattern.structual.composite.demo;


public class Employee implements Company {
    String name;
    String position;
    int salary;

    Employee(String name, String position, int salary) {
        this.name = name;
        this.position = position;
        this.salary = salary;
    }

    @Override
    public String getInfo() {
        String info = "";
        info = "名称:  " + name + "\n";
        info += "职位:  " + position + "\n";
        info += "薪水:  " + salary + "\n";
        return info;
    }
}
