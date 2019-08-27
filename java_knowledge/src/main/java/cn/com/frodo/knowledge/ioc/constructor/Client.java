package cn.com.frodo.knowledge.ioc.constructor;

import cn.com.frodo.knowledge.ioc.FloppyWriter;
import cn.com.frodo.knowledge.ioc.IDeviceWriter;
import cn.com.frodo.knowledge.ioc.USBWriter;

/**
 * Ioc（Inversion of Control）中文翻译为“控制反转”，Ioc的抽象概念是依赖关系转移，即“程序与实现必须依赖于抽象”。
 * 
 * 控制反转最常见的实现方式就是依赖注入（Depedency * Injection）
 * 依赖注入的含义是：保留抽象接口，让组件依赖抽象接口，当组件要与其他对象发生依赖关系时，通过抽象接口来注入依赖的实际对象。
 * 
 * 在解说Ioc之前，先看一个设计的例子： 设计一个保存数据到软盘、USB设备的程序。
 * 
 * @author frodoking Ioc constructor方式
 */
public class Client {

	public static void main(String[] args) {
		// 构造两个组件对象
		IDeviceWriter floppyWriter = new FloppyWriter();
		IDeviceWriter usbWriter = new USBWriter();

		// 构造两个业务对象
		BusinessObject businessObject1 = new BusinessObject(floppyWriter);
		BusinessObject businessObject2 = new BusinessObject(usbWriter);

		// 执行业务逻辑对象的保存方法
		businessObject1.save();
		System.out.println();
		businessObject2.save();

	}
}
