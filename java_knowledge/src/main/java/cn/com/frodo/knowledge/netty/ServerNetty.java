package com.frodo.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import io.netty.channel.socket.SocketChannel;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class ServerNetty
{

    private int port;

    public ServerNetty(int port)
    {
        this.port = port;
    }

    public void start() throws InterruptedException
    {
        EventLoopGroup bossGroup = new NioEventLoopGroup(3, new ThreadFactory()
        {
            AtomicInteger index = new AtomicInteger(0);

            @Override public Thread newThread(Runnable r)
            {
                return new Thread(r, "boss group:" + index.incrementAndGet());
            }
        });
        EventLoopGroup workerGroup = new NioEventLoopGroup(10, new ThreadFactory()
        {
            AtomicInteger index = new AtomicInteger(0);

            @Override public Thread newThread(Runnable r)
            {
                return new Thread(r,"worker group:" + index.incrementAndGet());
            }
        });

        try
        {
            ServerBootstrap sbs = new ServerBootstrap();
            sbs.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 128).childOption(ChannelOption.SO_KEEPALIVE, true)
                    .handler(new LoggingHandler(LogLevel.TRACE)).childHandler(new ChannelInitializer<SocketChannel>()
            {
                @Override protected void initChannel(SocketChannel socketChannel) throws Exception
                {
                    socketChannel.pipeline()
                            .addLast(new ServerHandler());//.addFirst(new LoggingHandler(LogLevel.INFO));
                }
            });
            ChannelFuture cf = sbs.bind(port).sync();

            // 等待服务端口的关闭；在这个例子中不会发生，但你可以优雅实现；关闭你的服务
            cf.channel().closeFuture().sync();
        }
        finally
        {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

}
