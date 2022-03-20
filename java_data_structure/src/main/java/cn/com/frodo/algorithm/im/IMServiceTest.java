package cn.com.frodo.algorithm.im;

import org.junit.Assert;

import java.util.concurrent.TimeUnit;

/**
 *
 * 题目：完成一个在线客服系统设计，公司客服部门内部有接线员，主管，经理三个角色，
 * 他们之间是逐层汇报的关系，接线员可以分多个组，每个组对应一个主管，多个主管对应一个经理，
 * 电话来了之后，会优先分配给接线员，如果这一组繁忙，转发给主管，如果主管繁忙，转发给经理。
 * 要求使用面向对象的设计方式完成系统设计和单测编写。
 *
 * 接线员单元测试服务
 *
 * @author frodoking
 * @ClassName: IMServiceTest
 * @date 2022/3/16
 */
public class IMServiceTest {

    private final IMService imService;

    public IMServiceTest() {
        imService = new IMServiceImpl();
    }

    public IMWorker initDefaultDirector() {
        IMReceiver imDirector = new IMReceiver(IMReceiverIdentityEnum.DIRECTOR);
        for (int i = 0; i < 3; i++) {
            IMReceiver imManager = new IMReceiver(imDirector, IMReceiverIdentityEnum.MANAGER);
            imDirector.addSubordinate(imManager);
            for (int j = 0; j < 5; j++) {
                IMReceiver imReceiver = new IMReceiver(imManager, IMReceiverIdentityEnum.RECEIVER);
                imManager.addSubordinate(imReceiver);
            }
        }
        return imDirector;
    }

    /**
     * 测试都是Idle的情况
     */
    public void allIdleTest() {
        IMWorker imDirector = initDefaultDirector();
        imService.setOrganization(imDirector);
        IMWorker imWorker = imService.findAndConnectReceiver(1);
        Assert.assertNotNull(imWorker);
    }

    /**
     * 测试部分组接线员忙的情况
     */
    public void oneReceiverBusyTest() {
        IMWorker imDirector = initDefaultDirector();
        imService.setOrganization(imDirector);

        new Thread(new Runnable() {
            @Override
            public void run() {
                imDirector.getSubordinates().get(0).getSubordinates().parallelStream().forEach(c -> c.call(5));
            }
        }).start();

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        IMWorker imWorker = imService.findAndConnectReceiver(3);
        Assert.assertNotNull(imWorker);
    }

    /**
     * 测试部分组接线员忙的情况
     */
    public void oneGroupBusyTest() {
        IMWorker imDirector = initDefaultDirector();
        imService.setOrganization(imDirector);

        new Thread(new Runnable() {
            @Override
            public void run() {
                imDirector.getSubordinates().get(0).getSubordinates().parallelStream().forEach(c -> c.call(5));
                imDirector.getSubordinates().get(0).call(5);
            }
        }).start();

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        IMWorker imWorker = imService.findAndConnectReceiver(3);
        Assert.assertNotNull(imWorker);
    }

    /**
     * 测试只有经理空闲的情况
     */
    public void onlyDirectorIdleTest() {
        IMWorker imDirector = initDefaultDirector();
        imService.setOrganization(imDirector);

        new Thread(new Runnable() {
            @Override
            public void run() {
                imDirector.getSubordinates().get(0).getSubordinates().parallelStream().forEach(c -> c.call(30));
                imDirector.getSubordinates().get(1).getSubordinates().parallelStream().forEach(c -> c.call(30));
                imDirector.getSubordinates().get(2).getSubordinates().parallelStream().forEach(c -> c.call(30));
                imDirector.getSubordinates().parallelStream().forEach(c -> c.call(30));
            }
        }).start();

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        IMWorker imWorker = imService.findAndConnectReceiver(30);
        Assert.assertNotNull(imWorker);
        Assert.assertEquals(imWorker, imDirector);
    }

    public void allBusyTest() {
        IMWorker imDirector = initDefaultDirector();
        imService.setOrganization(imDirector);

        new Thread(new Runnable() {
            @Override
            public void run() {
                imDirector.getSubordinates().get(0).getSubordinates().parallelStream().forEach(c -> c.call(30));
                imDirector.getSubordinates().get(1).getSubordinates().parallelStream().forEach(c -> c.call(30));
                imDirector.getSubordinates().get(2).getSubordinates().parallelStream().forEach(c -> c.call(30));
                imDirector.getSubordinates().parallelStream().forEach(c -> c.call(30));
                imDirector.call(30);
            }
        }).start();

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        IMWorker imWorker = imService.findAndConnectReceiver(1);
        Assert.assertNotNull(imWorker);
    }

    public static void main(String[] args) {
        IMServiceTest test = new IMServiceTest();
//        test.allIdleTest();
//        test.oneReceiverBusyTest();
//        test.oneGroupBusyTest();
//        test.onlyDirectorIdleTest();
        test.allBusyTest();
    }
}
