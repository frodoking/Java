package cn.com.frodo.knowledge.ioc;

public class USBWriter implements IDeviceWriter {

    @Override
    public void saveToDevice() {
        System.out.println("调用USBWriter.saveToDevice()方法: 保存到了USB设备上!");
    }
}
