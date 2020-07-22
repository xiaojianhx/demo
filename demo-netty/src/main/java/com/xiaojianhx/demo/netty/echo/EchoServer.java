package com.xiaojianhx.demo.netty.echo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.SelfSignedCertificate;

public class EchoServer {

    static final boolean SSL = System.getProperty("ssl") != null;
    static final int PORT = Integer.parseInt(System.getProperty("port", "8007"));

    public static void main(String[] args) throws Exception {

        final SslContext sslCtx;

        if (SSL) {
            var ssc = new SelfSignedCertificate();
            sslCtx = SslContextBuilder.forServer(ssc.certificate(), ssc.privateKey()).build();
        } else {
            sslCtx = null;
        }

        var boss = new NioEventLoopGroup(1);
        var worker = new NioEventLoopGroup();

        var server = new ServerBootstrap();
        server.group(boss, worker);
        server.channel(NioServerSocketChannel.class);
        server.option(ChannelOption.SO_BACKLOG, 100);
        server.handler(new LoggingHandler(LogLevel.INFO));
        server.childHandler(new ChannelInitializer<Channel>() {

            protected void initChannel(Channel ch) throws Exception {
                var p = ch.pipeline();
                if (sslCtx != null) {
                    p.addLast(sslCtx.newHandler(ch.alloc()));
                }
                p.addLast(new EchoServerHandler());
            }
        });

        var f = server.bind(PORT).sync();
        f.channel().closeFuture().sync();
    }
}
