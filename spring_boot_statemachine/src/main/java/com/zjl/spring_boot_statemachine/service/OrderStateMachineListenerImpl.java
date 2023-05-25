package com.zjl.spring_boot_statemachine.service;

import com.zjl.spring_boot_statemachine.dao.OrderDao;
import com.zjl.spring_boot_statemachine.enums.OrderEventEnum;
import com.zjl.spring_boot_statemachine.enums.OrderStatusEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.annotation.OnStateMachineError;
import org.springframework.statemachine.annotation.OnStateMachineStart;
import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.annotation.WithStateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.stereotype.Component;

/**
 * @classname: OrderStateMachineListenerImpl
 * @author: zhou
 * @description:
 * @date: 2023/5/23 17:35
 */
@Slf4j
@Component
@WithStateMachine(id = "orderStatusStateMachine")
@RequiredArgsConstructor
public class OrderStateMachineListenerImpl{

    private final OrderDao orderDao;

    private final StateMachinePersister<OrderStatusEnum, OrderEventEnum,String> stateMachinePersister;

    private final StateMachineFactory<OrderStatusEnum,OrderEventEnum> stateMachineFactory;

    @OnStateMachineError
    public void stateMachineError(StateMachine<OrderStatusEnum,OrderEventEnum> stateMachine,Exception e){
        log.error(e.toString(),e);
        log.error("stateMachine error");
    }

    @OnStateMachineStart
    public void onCreate(StateMachine<OrderStatusEnum, OrderEventEnum> stateMachine){
        log.info("stateMachineStart");
    }

    @OnTransition(source = "WAIT_PAY",target = "WAIT_SEND")
    public void onPay(Message<OrderStatusEnum> message){
        log.info("pay");
        MessageHeaders headers = message.getHeaders();
        String customerId = (String) headers.get("customerId");
        String productId = (String) headers.get("productId");
        String id = customerId + ":" + productId;
        orderDao.updateStatus(id,OrderStatusEnum.WAIT_SEND.getCode());
    }

    @OnTransition(source = "WAIT_SEND",target = "WAIT_RECEIVED")
    public void onSend(Message<OrderStatusEnum> message){
        log.info("send");
        MessageHeaders headers = message.getHeaders();
        String customerId = (String) headers.get("customerId");
        String productId = (String) headers.get("productId");
        String id = customerId + ":" + productId;
        orderDao.updateStatus(id,OrderStatusEnum.WAIT_RECEIVED.getCode());
    }

    @OnTransition(source = "WAIT_RECEIVED",target = "SUCCESS")
    public void onConfirm(Message<OrderStatusEnum> message){
        log.info("confirm");
        MessageHeaders headers = message.getHeaders();
        String customerId = (String) headers.get("customerId");
        String productId = (String) headers.get("productId");
        String id = customerId + ":" + productId;
        orderDao.updateStatus(id,OrderStatusEnum.SUCCESS.getCode());
    }

}
