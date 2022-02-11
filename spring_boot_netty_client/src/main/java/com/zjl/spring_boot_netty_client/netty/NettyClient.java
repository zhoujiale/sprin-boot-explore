package com.zjl.spring_boot_netty_client.netty;

import com.zjl.spring_boot_netty_client.config.NettyConfiguration;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.handler.ssl.SslContext;
import io.netty.util.CharsetUtil;
import io.netty.util.internal.SocketUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;

/**
 * @author zhou
 * @version 1.0
 * @className NettyClient
 * @description
 * @date 2022/02/10 15:25
 **/
@Slf4j
@Component
public class NettyClient {

    @Autowired
    private NettyConfiguration nettyConfiguration;

    static final int RECONNECT_DELAY = 5;

    static final int READ_TIMEOUT = 10;

    EventLoopGroup eventLoopGroup = new NioEventLoopGroup();

    private static final Bootstrap bootstrap = new Bootstrap();

    private static final NettyClientHandler nettyClientHandler = new NettyClientHandler();

    @PostConstruct
    public void start() throws InterruptedException, IOException {
        log.info("nettyClient start方法开始");
        final SslContext sslCtx = null;
        try {
            bootstrap.group(eventLoopGroup)
                    .channel(NioDatagramChannel.class)
                    .option(ChannelOption.SO_BROADCAST,true)
                    .handler(new NettyClientHandler());
            Channel channel = bootstrap.bind(0).sync().channel();
            channel.writeAndFlush(new DatagramPacket(Unpooled.copiedBuffer("QOTM?", CharsetUtil.UTF_8),
                    SocketUtils.socketAddress("127.0.0.1",nettyConfiguration.getPort())));
            if (!channel.closeFuture().await(5000)){
                System.err.println("QOTM request timed out.");
            }
        }finally {
            eventLoopGroup.shutdownGracefully();
        }
    }

}
