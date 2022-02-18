package com.zjl.commons.util.netty;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zhou
 * @version 1.0
 * @className BaseEntity
 * @description netty 通信基础类
 * @date 2022/02/17 20:22
 **/
@Data
public abstract class BaseEntity implements Serializable {

    /**
     * 标志
     **/
    private Integer tag = 1;

    /**
     * 指令
     **/
    public abstract Byte code();
}
