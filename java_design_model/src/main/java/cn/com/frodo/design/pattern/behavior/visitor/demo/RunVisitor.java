package cn.com.frodo.design.pattern.behavior.visitor.demo;

public class RunVisitor implements ComputerVisitor {

    @Override
    public void visitCPU(CPU cpu) {
        cpu.run();
    }

    @Override
    public void visitHarddisk(Harddisk harddisk) {
        harddisk.run();
    }
}
