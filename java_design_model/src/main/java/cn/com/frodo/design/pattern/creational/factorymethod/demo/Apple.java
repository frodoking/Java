package cn.com.frodo.design.pattern.creational.factorymethod.demo;

public class Apple implements Fruit {
	private int treeAge;

	public int getTreeAge() {
		return treeAge;
	}

	public void setTreeAge(int treeAge) {
		this.treeAge = treeAge;
	}

	@Override
	public void plant() {
		System.out.println("栽种苹果...");
	}

	@Override
	public void grow() {
		System.out.println("苹果正在生长...");
	}

	@Override
	public void harvest() {
		System.out.println("收获苹果...");
	}

}
