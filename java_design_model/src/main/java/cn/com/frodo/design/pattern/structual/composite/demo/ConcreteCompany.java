package cn.com.frodo.design.pattern.structual.composite.demo;

import java.util.ArrayList;

/**
 * 树枝节点
 *
 * @author frodoking
 */
public class ConcreteCompany implements Company {

    private ArrayList<Company> companyLst = new ArrayList<Company>();

    String name;
    String position;
    int salary;

    ConcreteCompany(String name, String position, int salary) {
        this.name = name;
        this.position = position;
        this.salary = salary;
    }

    public void add(Company company) {
        companyLst.add(company);
    }

    public void remove(int index) {
        companyLst.remove(index);
    }

    public ArrayList<Company> getChildren() {
        return companyLst;
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
