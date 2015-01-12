package cn.com.frodo.design.pattern.creational.builder.pattern;

public class ComputerDirector {
	ComputerBuilder builder;

	public T410 constructT410() {
		builder = new T410Builder();
		builder.buildCpu();
		builder.buildGraphicCard();
		builder.buildHardDisk();
		builder.buildMonitor();
		builder.buildOs();
		builder.buildRam();
		return (T410) builder.getResult();
	}

	public X201 constructX201() {
		builder = new X201Builder();
		builder.buildCpu();
		builder.buildGraphicCard();
		builder.buildHardDisk();
		builder.buildMonitor();
		builder.buildOs();
		builder.buildRam();
		return (X201) builder.getResult();
	}
}
