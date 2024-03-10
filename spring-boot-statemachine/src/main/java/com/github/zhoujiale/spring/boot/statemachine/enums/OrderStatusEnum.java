package com.github.zhoujiale.spring.boot.statemachine.enums;

import lombok.Getter;

/**
 * @classname: OrderStatusEnum
 * @author: zhou
 * @description: 订单状态枚举
 * @date: 2023/5/23 13:59
 */
@Getter
public enum OrderStatusEnum {

    CLOSE(-1,"订单关闭"),
    WAIT_PAY(0,"待支付"),
    WAIT_SEND(1,"待发货"),
    WAIT_RECEIVED(2,"待收货"),
    SUCCESS(3,"交易成功")

    ;
    private Integer code;

    private String msg;

    OrderStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
