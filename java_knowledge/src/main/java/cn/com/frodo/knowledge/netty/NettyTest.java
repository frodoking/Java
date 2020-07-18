package cn.com.frodo.knowledge.netty;

import cn.com.frodo.MockInterface;

public class NettyTest implements MockInterface {
    @Override
    public void doTest() {
        int port = 8030;
        for (int i = 0; i < 15; i++) {
            new Thread(() -> {
                try {
                    ClientNetty clientNetty = new ClientNetty("127.0.0.1", port);
                    for (; ; ) {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        clientNetty.sendMsg();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        try {
            new ServerNetty(port).start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
