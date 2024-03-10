package com.github.zhoujiale.spring.boot.statemachine.enums;

import lombok.Getter;

/**
 * @classname: OrderEventEnum
 * @author: zhou
 * @description: 订单时间
 * @date: 2023/5/23 14:11
 */
@Getter
public enum OrderEventEnum {

    PAY,
    SEND,
    CONFIRM
}
