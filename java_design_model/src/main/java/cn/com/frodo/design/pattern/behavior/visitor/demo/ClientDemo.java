package cn.com.frodo.design.pattern.behavior.visitor.demo;

public class ClientDemo {

	public static void main(String[] args) {
		Computer computer = new Computer();

		ComputerVisitor typeVisitor = new TypeVisitor();
		ComputerVisitor runVisitor = new RunVisitor();

		computer.accept(typeVisitor);
		System.out.println("---------------------------");
		computer.accept(runVisitor);
	}
}
