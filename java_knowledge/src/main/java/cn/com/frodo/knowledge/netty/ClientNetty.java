package com.frodo.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.apache.commons.io.Charsets;

import java.util.Date;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class ClientNetty
{
    // 要请求的服务器的ip地址
    private String ip;

    // 服务器的端口
    private int port;

    ChannelFuture channelFuture;

    public ClientNetty(String ip, int port) throws InterruptedException
    {
        this.ip = ip;
        this.port = port;

        EventLoopGroup group = new NioEventLoopGroup(3, new ThreadFactory()
        {
            AtomicInteger index = new AtomicInteger(0);

            @Override public Thread newThread(Runnable r)
            {
                return new Thread(r, "client group:" + index.incrementAndGet());
            }
        });

        Bootstrap bs = new Bootstrap();

        bs.group(group).channel(NioSocketChannel.class).option(ChannelOption.SO_KEEPALIVE, true)
                .handler(new ChannelInitializer<SocketChannel>()
                {
                    @Override protected void initChannel(SocketChannel socketChannel) throws Exception
                    {
                        socketChannel.pipeline().addLast(new ClientHandler()); //.addFirst(new LoggingHandler(LogLevel.INFO)).addLast(new LoggingHandler(LogLevel.INFO));
                    }
                });

        // 客户端开启
        channelFuture = bs.connect(ip, port).sync();
    }

    // 请求端主题
    public void sendMsg()
    {
        String reqStr = "我是客户端请求 At: " + new Date().toString();
        channelFuture.channel().writeAndFlush(Unpooled.copiedBuffer(reqStr.getBytes(Charsets.UTF_8)));

        // 等待直到连接中断
        // channelFuture.channel().closeFuture().sync();
    }
}
