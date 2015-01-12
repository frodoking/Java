package cn.com.frodo.design.pattern.creational.builder.pattern;

public class X201Builder implements ComputerBuilder {
	private X201 x201 = new X201();

	@Override
	public void buildCpu() {
		x201.setCpu("i5-450");
	}

	@Override
	public void buildRam() {
		x201.setRam("4GB 1333MHZ");
	}

	@Override
	public void buildHardDisk() {
		x201.setHardDisk("500GB 7200转");
	}

	@Override
	public void buildGraphicCard() {
	}

	@Override
	public void buildMonitor() {
		x201.setMonitor("14 英寸 1280 * 800");
	}

	@Override
	public void buildOs() {
		x201.setOs("windows 7");
	}

	@Override
	public Computer getResult() {
		return x201;
	}

}
