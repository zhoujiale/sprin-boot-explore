package com.zjl.spring_boot_netty.server;

import com.zjl.commons.util.netty.*;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.AttributeKey;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhou
 * @version 1.0
 * @className NettyServerHandler
 * @description
 * @date 2022/02/10 11:08
 **/
@Slf4j
@ChannelHandler.Sharable
public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    private static Map<Integer, Channel> map = new HashMap<Integer, Channel>();
    
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("first handler");
        super.channelActive(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {
        if(o instanceof LoginReqEntity){
            //登录
            login((LoginReqEntity)o,channelHandlerContext.channel());
        }else {
            //消息
            sendMsg((MsgReqEntity)o,channelHandlerContext.channel());
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
    
    private void login(LoginReqEntity loginReqEntity,Channel channel){
        LoginResEntity loginResEntity = new LoginResEntity();

        Channel c = map.get(loginReqEntity.getUserId());

        if (null == c){
            map.put(loginReqEntity.getUserId(),channel);
            channel.attr(AttributeKey.valueOf("userId")).set(loginReqEntity.getUserId());;
            loginResEntity.setStatus(0);
            loginResEntity.setMsg("登录成功");
            loginResEntity.setUserId(loginReqEntity.getUserId());
            channel.writeAndFlush(loginResEntity);
        }else {
            loginResEntity.setStatus(1);
            loginResEntity.setMsg("已在线");
            channel.writeAndFlush(loginResEntity);
        }
    }

    private void sendMsg(MsgReqEntity msgReqEntity,Channel channel){
        Integer toUserId = msgReqEntity.getToUserId();
        Channel c = map.get(toUserId);
        if (null == c){
            MsgResEntity msgResEntity = new MsgResEntity();
            msgResEntity.setStatus(1);
            msgResEntity.setMsg(toUserId+",不在线");
            channel.writeAndFlush(msgResEntity);
        }else {
            MsgRecEntity msgRecEntity = new MsgRecEntity();
            msgRecEntity.setFromUserId(msgReqEntity.getFromUserId());
            msgRecEntity.setMsg(msgReqEntity.getMsg());
            c.writeAndFlush(msgRecEntity);
        }
    }
}
