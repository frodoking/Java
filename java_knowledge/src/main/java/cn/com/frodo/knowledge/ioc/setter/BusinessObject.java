package cn.com.frodo.knowledge.ioc.setter;

import cn.com.frodo.knowledge.ioc.IDeviceWriter;

public class BusinessObject {

    private IDeviceWriter writer;

    public IDeviceWriter getWriter() {
        return writer;
    }

    public void setWriter(IDeviceWriter writer) {
        this.writer = writer;
    }

    /**
     * 通用的保存业务方法
     */
    public void save() {
        System.out.println("----开始调用业务组件BusinessObject的save()方法....");
        writer.saveToDevice();
        System.out.println("----结束调用业务组件BusinessObject的save()方法....");
    }

}
