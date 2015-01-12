package cn.com.frodo.design.pattern.behavior.visitor.demo;

public class TypeVisitor implements ComputerVisitor {

	@Override
	public void visitCPU(CPU cpu) {
		System.out.println("-----" + cpu.getType() + "-------");
	}

	@Override
	public void visitHarddisk(Harddisk harddisk) {
		System.out.println("-----" + harddisk.getType() + "-------");
	}
}
