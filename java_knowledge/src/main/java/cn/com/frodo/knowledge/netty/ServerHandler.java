package cn.com.frodo.knowledge.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.apache.commons.io.Charsets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class ServerHandler extends ChannelInboundHandlerAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(ServerHandler.class);

    // 读取数据
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf bb = (ByteBuf) msg;
        byte[] reqByte = new byte[bb.readableBytes()];
        bb.readBytes(reqByte);
        String reqStr = new String(reqByte, Charsets.UTF_8);
        LOGGER.warn("[ {} : {} / {}] - server 接收到客户端的请求: {}", ctx.channel(), ctx.channel().localAddress(),  ctx.channel().remoteAddress(), reqStr);
        String respStr = "服务器的响应 >> " + ctx.channel().remoteAddress() + " << At: " + new Date();
        ctx.writeAndFlush(Unpooled.copiedBuffer(respStr.getBytes()));
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
//        LOGGER.info("sever complete");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
//        LOGGER.error("server exception");
        ctx.close();
    }

}
