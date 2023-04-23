package cn.com.frodo.knowledge.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.concurrent.DefaultThreadFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class ServerNetty {

    private final int[] ports;

    public ServerNetty(int[] ports) {
        this.ports = ports;
    }

    public void start() throws InterruptedException {
        EventLoopGroup bossGroup = new NioEventLoopGroup(ports.length, new DefaultThreadFactory("Boss"));
        EventLoopGroup workerGroup = new NioEventLoopGroup(ports.length * 10, new DefaultThreadFactory("Worker"));

        // 非I/O事件处理
        AtomicInteger i =  new AtomicInteger(0);
        EventLoopGroup business = new DefaultEventLoopGroup(10, r -> {
            return new Thread(r, "business-" + i.incrementAndGet());
        });

        try {
            ServerBootstrap sbs = new ServerBootstrap();
            sbs.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 128).childOption(ChannelOption.SO_KEEPALIVE, true)
//                    .handler(new LoggingHandler(LogLevel.TRACE))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) {
                            socketChannel.pipeline()
                                    .addLast(business, new ServerHandler());
//                                    .addFirst(new LoggingHandler(LogLevel.INFO));
                        }
                    });
            List<ChannelFuture> channelFutures = new ArrayList<>();

            for (int port : ports) {
                channelFutures.add(sbs.bind(port).sync());
            }

            // 等待服务端口的关闭；在这个例子中不会发生，但你可以优雅实现；关闭你的服务
            channelFutures.forEach(channelFuture -> {
                try {
                    channelFuture.channel().closeFuture().sync();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

}
