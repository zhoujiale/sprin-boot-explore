package com.github.zhoujiale.spring.boot.netty.client.netty;

import com.github.zhoujiale.commons.util.netty.*;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.AttributeKey;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

/**
 * @author zhou
 * @version 1.0
 * @className NettyClientHandler
 * @description
 * @date 2022/02/10 15:32
 **/
@Slf4j
@ChannelHandler.Sharable
public class NettyClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //登录
        this.login(ctx.channel());
    }

    /**
     * @description 登录请求
     * @author zhou
     * @create 2022/2/17 21:23 
     * @param 
     * @return void
     **/
    private void login(Channel channel) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(">>用户ID：");
        int userId = scanner.nextInt();
        System.out.println(">>用户名称：");
        String username = scanner.next();

        LoginReqEntity loginReqEntity = new LoginReqEntity();
        loginReqEntity.setUserId(userId);
        loginReqEntity.setUserName(username);
        channel.writeAndFlush(loginReqEntity);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof LoginResEntity){
            //登录响应
            LoginResEntity res = (LoginResEntity)msg;
            System.out.println(">>>>>登录响应:" + res.getMsg());
            if (res.getStatus() == 0){
                //成功
                ctx.channel().attr(AttributeKey.valueOf("userId")).set(res.getUserId());;
                //发送消息
                sendMsg(ctx.channel());
            }else {
                login(ctx.channel());
            }
        }else if (msg instanceof MsgResEntity){
            MsgResEntity msgResEntity = (MsgResEntity)msg;
            System.out.println(">>>>>>发送响应："+ msgResEntity.getMsg());
        }else if (msg instanceof MsgRecEntity){
            MsgRecEntity msgRecEntity = (MsgRecEntity)msg;
            System.out.println("fromUserId="+msgRecEntity.getFromUserId()+",msg="+msgRecEntity.getMsg());
        }
    }

    private void sendMsg(final Channel channel){
        final Scanner scanner = new Scanner(System.in);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    System.out.println(">>接收人ID：");
                    int nextInt = scanner.nextInt();
                    System.out.println(">>聊天内容:");
                    String msg = scanner.next();

                    MsgReqEntity msgReqEntity = new MsgReqEntity();
                    Integer fromUserId = (Integer) channel.attr(AttributeKey.valueOf("userId")).get();
                    msgReqEntity.setFromUserId(fromUserId);
                    msgReqEntity.setToUserId(nextInt);
                    msgReqEntity.setMsg(msg);
                    channel.writeAndFlush(msgReqEntity);
                }
            }
        }).start();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
