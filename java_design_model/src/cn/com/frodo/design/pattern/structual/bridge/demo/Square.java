package cn.com.frodo.design.pattern.structual.bridge.demo;

public class Square extends AbstractShape {

	Square(Color color) {
		super(color);
	}

	@Override
	public void onDraw() {
		System.out.println("使用" + color.getColor() + "画正方形");
	}
}
