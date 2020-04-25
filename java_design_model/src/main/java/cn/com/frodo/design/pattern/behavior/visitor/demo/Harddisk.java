package cn.com.frodo.design.pattern.behavior.visitor.demo;

public class Harddisk extends Hardware {

    public Harddisk(String type) {
        super(type);
    }

    @Override
    public void run() {
        System.out.println("Harddisk为： " + type + "    正在运转");
    }

    @Override
    public void accept(ComputerVisitor computerVisitor) {
        computerVisitor.visitHarddisk(this);
    }
}
