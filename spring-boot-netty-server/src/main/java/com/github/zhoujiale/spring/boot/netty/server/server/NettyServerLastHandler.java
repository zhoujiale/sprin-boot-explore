package com.github.zhoujiale.spring.boot.netty.server.server;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author zhou
 * @version 1.0
 * @className NettyServerLastHandler
 * @description
 * @date 2022/02/14 11:23
 **/
@Slf4j
@ChannelHandler.Sharable
public class NettyServerLastHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("last handler");
    }
}
