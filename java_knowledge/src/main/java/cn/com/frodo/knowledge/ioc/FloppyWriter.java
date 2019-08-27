package cn.com.frodo.knowledge.ioc;

public class FloppyWriter implements IDeviceWriter {

	@Override
	public void saveToDevice() {
		System.out.println("调用FloppyWriter.saveToDevice()方法: 保存到了Floppy设备上!");
	}
}
