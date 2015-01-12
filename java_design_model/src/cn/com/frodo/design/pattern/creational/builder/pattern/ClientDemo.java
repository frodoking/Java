package cn.com.frodo.design.pattern.creational.builder.pattern;

public class ClientDemo {
	public static void main(String[] args) {
		ComputerDirector director = new ComputerDirector();
		T410 t410 = director.constructT410();
		X201 x201 = director.constructX201();

		System.out.println(t410);
		System.out.println("-------------------------");
		System.out.println(x201);
	}
}
