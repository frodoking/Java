package cn.com.frodo.design.pattern.creational.builder.pattern;

public interface ComputerBuilder {
	void buildCpu();

	void buildRam();

	void buildHardDisk();

	void buildGraphicCard();

	void buildMonitor();

	void buildOs();

	Computer getResult();
}
