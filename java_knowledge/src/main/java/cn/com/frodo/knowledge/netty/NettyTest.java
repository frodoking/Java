package cn.com.frodo.knowledge.netty;

import cn.com.frodo.MockInterface;

import java.util.concurrent.TimeUnit;

public class NettyTest implements MockInterface {
    @Override
    public void doTest() {
        int[] ports = {8030, 8031, 8032};
//        int[] ports = {8030};

        startServer(ports);

        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        startClient(ports, 10);
    }

    private void startServer(int[] ports) {
        new Thread(() -> {
            try {
                new ServerNetty(ports).start();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void startClient(int[] ports, int threads) {
        for (int i = 0; i < threads * ports.length; i++) {
            int fi = i;
            new Thread(() -> {
                try {
                    ClientNetty clientNetty = new ClientNetty("127.0.0.1", ports[fi % ports.length]);
                    for (; ; ) {
                        try {
                            Thread.sleep(3000);
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
    }
}
