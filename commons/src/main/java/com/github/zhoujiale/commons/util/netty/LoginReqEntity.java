package com.github.zhoujiale.commons.util.netty;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author zhou
 * @version 1.0
 * @className LoginReqEntity
 * @description
 * @date 2022/02/17 20:55
 **/
@Data
@EqualsAndHashCode(callSuper = true)
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
