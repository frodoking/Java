package cn.com.frodo.design.pattern.structual.composite.demo;


public class ClientDemo {

    public static void main(String[] args) {
        ConcreteCompany ceo = new ConcreteCompany("boss", "CEO", 100000);

        ConcreteCompany cto = new ConcreteCompany("老二", "CTO", 80000);
        ConcreteCompany cfo = new ConcreteCompany("老三", "CFO", 50000);
        ConcreteCompany cpo = new ConcreteCompany("老四", "CPO", 30000);
        ceo.add(cto);
        ceo.add(cfo);
        ceo.add(cpo);

        Employee e1 = new Employee("A", "研发", 12000);
        Employee e2 = new Employee("B", "研发", 11000);
        Employee e3 = new Employee("C", "研发", 10000);
        cto.add(e1);
        cto.add(e2);
        cto.add(e3);

        Employee e4 = new Employee("M", "销售", 8000);
        Employee e5 = new Employee("N", "销售", 7000);
        cfo.add(e4);
        cfo.add(e5);
        Employee e6 = new Employee("P", "HR", 5000);
        Employee e7 = new Employee("O", "HR", 5000);
        cpo.add(e6);
        cpo.add(e7);

        System.out.println(ceo.getInfo());
        display(ceo);
    }

    public static void display(ConcreteCompany root) {
        for (Company c : root.getChildren()) {
            if (c instanceof Employee) {
                System.out.println(c.getInfo());
            } else {
                System.out.println(c.getInfo());
                display((ConcreteCompany) c);
            }
        }
    }
}
