package com.zjl.spring_boot_netty.server;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.zjl.spring_boot_netty.config.NettyConfiguration;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.ssl.SslContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author zhou
 * @version 1.0
 * @className NettyServer
 * @description
 * @date 2022/02/10 10:33
 **/
@Slf4j
@Component
public class NettyServer {

    @Autowired
    private NettyConfiguration nettyConfiguration;


    EventLoopGroup bossGroup = new NioEventLoopGroup(1);

    EventLoopGroup workGroup = new NioEventLoopGroup();

    /**
     * @param
     * @return void
     * @description 启动NettyServer
     * @author zhou
     * @create 2022/2/10 10:57
     **/
    //@PostConstruct
    public void start() throws InterruptedException {
        log.info("nettyServer start方法被调用");
        final SslContext sslContext = null;
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        final NettyServerHandler nettyServerHandler = new NettyServerHandler();
        serverBootstrap.group(bossGroup,workGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG,100)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        ChannelPipeline pipeline = socketChannel.pipeline();
                        pipeline.addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE,5,4));
                        pipeline.addLast(new SelfDecoder());
                        pipeline.addLast(nettyServerHandler);
                        pipeline.addLast(new SelfEncoder());
                    }
                });
        ChannelFuture channelFuture = serverBootstrap.bind(nettyConfiguration.getPort()).sync();
        log.info("netty 服务启动成功");
        channelFuture.channel().closeFuture().sync();
    }

    @PreDestroy
    public void destroy() {
        log.info("nettyServer 关闭");
        bossGroup.shutdownGracefully();
        workGroup.shutdownGracefully();
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
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
    
}
