package cn.com.frodo.design.pattern.structual.composite.pattern;

public class Clients {
	public static void main(String[] args) {
		Composite root = new Composite();
		Composite branch = new Composite();

		Leaf leaf = new Leaf();
		root.add(branch);
		branch.add(leaf);
		
		display(root);
	}

	public static void display(Composite root) {
		for (Component component : root.getLst()) {
			if (component instanceof Leaf) {
				component.operation();
			} else {
				component.operation();
				display((Composite) component);
			}
		}
	}
}
