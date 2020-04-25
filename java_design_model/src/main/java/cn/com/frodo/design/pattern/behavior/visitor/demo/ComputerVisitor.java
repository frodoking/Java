package cn.com.frodo.design.pattern.behavior.visitor.demo;

public interface ComputerVisitor {
    void visitCPU(CPU cpu);

    void visitHarddisk(Harddisk harddisk);
}
