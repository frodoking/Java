package cn.com.frodo.design.pattern.structual.bridge.demo;

public class ClientDemo {

	public static void main(String[] args) {
		Color red = new Red();
		Color green = new Green();
		AbstractShape circle = new Circle(red);
		AbstractShape square = new Square(green);
		circle.onDraw();
		square.onDraw();
	}
}
