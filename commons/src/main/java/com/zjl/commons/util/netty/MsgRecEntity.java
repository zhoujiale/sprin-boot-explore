package com.zjl.commons.util.netty;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zhou
 * @version 1.0
 * @className MsgRecEntity
 * @description
 * @date 2022/02/17 21:15
 **/
@Data
public class MsgRecEntity extends BaseEntity implements Serializable {

    private Integer fromUserId;

    private String msg;

    @Override
    public Byte code() {
        return 5;
    }
}
