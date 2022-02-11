package com.zjl.spring_boot_netty_client.netty;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author zhou
 * @version 1.0
 * @className NettyClientHandler
 * @description
 * @date 2022/02/10 15:32
 **/
@Slf4j
@ChannelHandler.Sharable
public class NettyClientHandler extends SimpleChannelInboundHandler<DatagramPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, DatagramPacket datagramPacket) throws Exception {
        String response = datagramPacket.content().toString(CharsetUtil.UTF_8);
        if (response.startsWith("QOTM:")) {
            System.out.println("Quote of the Moment:" + response.substring(6));
            channelHandlerContext.close();
        }
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
