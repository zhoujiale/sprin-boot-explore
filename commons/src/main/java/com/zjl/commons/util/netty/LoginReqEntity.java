package com.zjl.commons.util.netty;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zhou
 * @version 1.0
 * @className LoginReqEntity
 * @description
 * @date 2022/02/17 20:55
 **/
@Data
public class LoginReqEntity extends BaseEntity implements Serializable {

    /**用户id**/
    private Integer userId;

    /**用户名称**/
    private String userName;

    @Override
    public Byte code() {
        return 1;
    }
}
