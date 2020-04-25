package cn.com.frodo.design.pattern.behavior.visitor.demo;

public class CPU extends Hardware {

    public CPU(String type) {
        super(type);
    }

    @Override
    public void run() {
        System.out.println("cpu为： " + type + "    正在运转");
    }

    @Override
    public void accept(ComputerVisitor computerVisitor) {
        computerVisitor.visitCPU(this);
    }
}
