package com.xiaojianhx.demo.netty.echo;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class EchoClientHandler extends ChannelInboundHandlerAdapter {

    private final ByteBuf firstMessage;

    public EchoClientHandler() {

        firstMessage = Unpooled.buffer(EchoClient.SIZE);

        for (var i = 0; i < firstMessage.capacity(); i++) {
            firstMessage.writeByte((byte) i);
        }
    }

    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(firstMessage);
    }

    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ctx.write(msg);
    }

    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
