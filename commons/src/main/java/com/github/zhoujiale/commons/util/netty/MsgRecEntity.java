package com.github.zhoujiale.commons.util.netty;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author zhou
 * @version 1.0
 * @className MsgRecEntity
 * @description
 * @date 2022/02/17 21:15
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class MsgRecEntity extends BaseEntity implements Serializable {

    private Integer fromUserId;

    private String msg;

    @Override
    public Byte code() {
        return 5;
    }
}
