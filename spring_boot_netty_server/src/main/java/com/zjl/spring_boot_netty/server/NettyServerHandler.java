package com.zjl.spring_boot_netty.server;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;

/**
 * @author zhou
 * @version 1.0
 * @className NettyServerHandler
 * @description
 * @date 2022/02/10 11:08
 **/
@Slf4j
@ChannelHandler.Sharable
public class NettyServerHandler extends SimpleChannelInboundHandler<DatagramPacket> {

    private static final Random random = new Random();

    private static final String[] quotes = {
            "Where there is love there is life.",
            "First they ignore you, then they laugh at you, then they fight you, then you win.",
            "Be the change you want to see in the world.",
            "The weak can never forgive. Forgiveness is the attribute of the strong.",
    };

    private static String nextQuote() {
        int quoteId;
        synchronized (random) {
            quoteId = random.nextInt(quotes.length);
        }
        return quotes[quoteId];
    }



    @Override
    public void channelRead0(ChannelHandlerContext channelHandlerContext, DatagramPacket packet) throws Exception {
        System.err.println(packet);
        if ("QOTM?".equals(packet.content().toString(CharsetUtil.UTF_8))){
            channelHandlerContext.write(new DatagramPacket(Unpooled.copiedBuffer("QOTM:"+ nextQuote(),
                    CharsetUtil.UTF_8), packet.sender()));
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
