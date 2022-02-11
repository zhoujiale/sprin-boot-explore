package com.zjl.spring_boot_netty_client.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.ssl.SslContext;

/**
 * @author zhou
 * @version 1.0
 * @className TelnetClientInitializer
 * @description
 * @date 2022/02/10 18:35
 **/
public class TelnetClientInitializer extends ChannelInitializer<SocketChannel> {

    private static final StringDecoder DECODER = new StringDecoder();

    private static final StringEncoder ENCODER = new StringEncoder();

    private static final NettyClientHandler NETTY_CLIENT_HANDLER = new NettyClientHandler();

    private final SslContext sslCtx;

    public TelnetClientInitializer(SslContext sslCtx){
        this.sslCtx = sslCtx;
    }

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        if (sslCtx != null){
            pipeline.addLast(sslCtx.newHandler(socketChannel.alloc(),"127.0.0.1",8097));
        }
        pipeline.addLast(new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
        pipeline.addLast(DECODER);
        pipeline.addLast(ENCODER);

        pipeline.addLast(NETTY_CLIENT_HANDLER);
    }
}
