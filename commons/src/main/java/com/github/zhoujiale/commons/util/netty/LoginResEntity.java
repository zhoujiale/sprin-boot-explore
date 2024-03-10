package com.github.zhoujiale.commons.util.netty;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author zhou
 * @version 1.0
 * @className LoginResEntity
 * @description
 * @date 2022/02/17 20:57
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class LoginResEntity extends BaseEntity implements Serializable {

    /**
     * 响应状态 0登录成功 1登录失败
     **/
    private Integer status;

    /**
     * 响应信息
     **/
    private String msg;

    /**
     * 用户id
     **/
    private Integer userId;

    @Override
    public Byte code() {
        return 2;
    }
}
