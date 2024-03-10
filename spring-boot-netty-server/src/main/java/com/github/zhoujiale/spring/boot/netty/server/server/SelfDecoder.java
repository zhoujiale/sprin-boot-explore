package com.github.zhoujiale.spring.boot.netty.server.server;

import com.alibaba.fastjson2.JSON;
import com.github.zhoujiale.commons.util.netty.BaseEntity;
import com.github.zhoujiale.commons.util.netty.MapUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @author zhou
 * @version 1.0
 * @className SelfDecoder
 * @description 解码器
 * @date 2022/02/17 20:29
 **/
public class SelfDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        //标识符
        int tag = byteBuf.readInt();
        //获取指令
        byte code = byteBuf.readByte();
        //获取数据长度
        int len = byteBuf.readInt();
        //分配字节大小
        byte[] bytes = new byte[len];
        byteBuf.readBytes(bytes);

        Class<? extends BaseEntity> bean = MapUtils.getBean(code);

        //反序列化
        BaseEntity baseEntity  = JSON.parseObject(bytes, bean);

        list.add(baseEntity);
    }
}
