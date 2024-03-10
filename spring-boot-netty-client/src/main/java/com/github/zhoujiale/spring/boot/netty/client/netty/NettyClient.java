package com.github.zhoujiale.spring.boot.netty.client.netty;

import com.github.zhoujiale.spring.boot.netty.client.config.NettyConfiguration;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.ssl.SslContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

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

    //@PostConstruct
    public void start() throws InterruptedException, IOException {
        log.info("nettyClient start方法开始");
        final SslContext sslCtx = null;
        try {
            bootstrap.group(eventLoopGroup)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY,true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            ChannelPipeline pipeline = socketChannel.pipeline();
                            pipeline.addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE,5,4));
                            pipeline.addLast(new SelfDecoder());
                            pipeline.addLast(new NettyClientHandler());
                            pipeline.addLast(new SelfEncoder());
                        }
                    });
            ChannelFuture channelFuture = bootstrap.connect(nettyConfiguration.getHost(), nettyConfiguration.getPort())
                    .sync();
            channelFuture.channel().closeFuture().await();
        }finally {
            eventLoopGroup.shutdownGracefully();
        }
    }

    /**
     * @description 初始化NettyServer  通过一个新的线程来启动避免阻塞
     * @author zhou
     * @create 2022/2/11 16:43
     * @param
     * @return void
     **/
    @PostConstruct
    public void init(){
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
                .setNameFormat("netty-pool-%d").build();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 1, 0L,
                TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(1024),
                namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());
        threadPoolExecutor.execute(() -> {
            try {
                start();
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
        });
    }
}
