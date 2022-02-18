package com.zjl.spring_boot_netty.server;

import com.alibaba.fastjson.JSON;
import com.zjl.commons.util.netty.BaseEntity;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author zhou
 * @version 1.0
 * @className SelfEncoder
 * @description 编码实现
 * @date 2022/02/17 20:21
 **/
public class SelfEncoder extends MessageToByteEncoder<BaseEntity> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, BaseEntity baseEntity, ByteBuf byteBuf) throws Exception {
        //实体序列化成字节
        byte[] bytes = JSON.toJSONBytes(baseEntity);

        //协议组装
        byteBuf.writeInt(baseEntity.getTag());
        byteBuf.writeByte(baseEntity.code());
        byteBuf.writeInt(bytes.length);
        byteBuf.writeBytes(bytes);
    }
}
