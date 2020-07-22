package com.xiaojianhx.demo.netty.echo;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.SelfSignedCertificate;

public class EchoClient {

    static final boolean SSL = System.getProperty("ssl") != null;
    static final String HOST = System.getProperty("host", "localhost");
    static final int PORT = Integer.parseInt(System.getProperty("port", "8007"));
    static final int SIZE = Integer.parseInt(System.getProperty("size", "256"));

    public static void main(String[] args) throws Exception {

        final SslContext sslCtx;

        if (SSL) {
            var ssc = new SelfSignedCertificate();
            sslCtx = SslContextBuilder.forServer(ssc.certificate(), ssc.privateKey()).build();
        } else {
            sslCtx = null;
        }

        var group = new NioEventLoopGroup();

        var client = new Bootstrap();
        client.group(group);
        client.channel(NioSocketChannel.class);
        client.option(ChannelOption.TCP_NODELAY, true);
        client.handler(new ChannelInitializer<Channel>() {

            protected void initChannel(Channel ch) throws Exception {

                var p = ch.pipeline();
                if (sslCtx != null) {
                    p.addLast(sslCtx.newHandler(ch.alloc(), HOST, PORT));
                }
                p.addLast(new EchoClientHandler());
            }
        });

        var f = client.connect(HOST, PORT).sync();
        f.channel().closeFuture().sync();
    }
}