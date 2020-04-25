package cn.com.frodo.design.pattern.creational.builder.pattern;

public class T410Builder implements ComputerBuilder {
    private T410 t410 = new T410();

    @Override
    public void buildCpu() {
        t410.setCpu("i5-450");
    }

    @Override
    public void buildRam() {
        t410.setRam("4GB 1333MHZ");
    }

    @Override
    public void buildHardDisk() {
        t410.setHardDisk("500GB 7200转");
    }

    @Override
    public void buildGraphicCard() {
        t410.setGraphicCard("Nvidia NVS 3100Ms");
    }

    @Override
    public void buildMonitor() {
        t410.setMonitor("14 英寸 1280 * 800");
    }

    @Override
    public void buildOs() {
        t410.setOs("windows 7");
    }

    @Override
    public Computer getResult() {
        return t410;
    }

}
