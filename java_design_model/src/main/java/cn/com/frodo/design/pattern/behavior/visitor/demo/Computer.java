package cn.com.frodo.design.pattern.behavior.visitor.demo;

public class Computer {
    private Hardware cpu;
    private Hardware harddisk;

    public Computer() {
        cpu = new CPU("Intel Core i7-620");
        harddisk = new Harddisk("Seagate 500G 7200转");
    }

    public void accept(ComputerVisitor computerVisitor) {
        cpu.accept(computerVisitor);
        harddisk.accept(computerVisitor);
    }
}
