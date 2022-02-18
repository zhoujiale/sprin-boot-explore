package com.zjl.commons.util.netty;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zhou
 * @version 1.0
 * @className MsgResEntity
 * @description
 * @date 2022/02/17 21:11
 **/
@Data
public class MsgResEntity extends BaseEntity implements Serializable {

    /**
     * 响应状态 0 成功 1 失败
     **/
    private Integer status;

    /**
     * 响应信息
     **/
    private String msg;

    @Override
    public Byte code() {
        return 4;
    }
}
