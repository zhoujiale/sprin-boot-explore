package com.github.zhoujiale.spring.boot.statemachine.config;

import com.github.zhoujiale.spring.boot.statemachine.enums.OrderEventEnum;
import com.github.zhoujiale.spring.boot.statemachine.enums.OrderStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import java.util.EnumSet;

/**
 * @classname: OrderStatusStateMachineConfig
 * @author: zhou
 * @description: 订单状态机
 * @date: 2023/5/23 14:10
 */
@Slf4j
@Configuration
@EnableStateMachineFactory
public class OrderStatusStateMachineConfig extends EnumStateMachineConfigurerAdapter<OrderStatusEnum, OrderEventEnum> {

    public static final String STATE_MACHINE = "orderStatusStateMachine";

    @Override
    public void configure(StateMachineConfigurationConfigurer<OrderStatusEnum, OrderEventEnum> config) throws Exception {
        config.withConfiguration()
                .machineId(STATE_MACHINE)
                .autoStartup(true);
    }

    @Override
    public void configure(StateMachineStateConfigurer<OrderStatusEnum, OrderEventEnum> states) throws Exception {
        states.withStates()
                .initial(OrderStatusEnum.WAIT_PAY)
                .states(EnumSet.allOf(OrderStatusEnum.class));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<OrderStatusEnum, OrderEventEnum> transitions) throws Exception {
        //配置状态和对应触发的事件
        transitions
                .withExternal().source(OrderStatusEnum.WAIT_PAY).target(OrderStatusEnum.WAIT_SEND).event(OrderEventEnum.PAY)
                .and()
                .withExternal().source(OrderStatusEnum.WAIT_SEND).target(OrderStatusEnum.WAIT_RECEIVED).event(OrderEventEnum.SEND)
                .and()
                .withExternal().source(OrderStatusEnum.WAIT_RECEIVED).target(OrderStatusEnum.SUCCESS).event(OrderEventEnum.CONFIRM);
    }

}
