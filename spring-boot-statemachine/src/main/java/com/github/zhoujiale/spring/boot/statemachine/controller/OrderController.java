package com.github.zhoujiale.spring.boot.statemachine.controller;

import com.github.zhoujiale.commons.util.response.WebResponse;
import com.github.zhoujiale.spring.boot.statemachine.config.OrderStatusStateMachineConfig;
import com.github.zhoujiale.spring.boot.statemachine.dao.OrderDao;
import com.github.zhoujiale.spring.boot.statemachine.enums.OrderEventEnum;
import com.github.zhoujiale.spring.boot.statemachine.enums.OrderStatusEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @classname: OrderController
 * @author: zhou
 * @description: 订单控制器
 * @date: 2023/5/23 15:22
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/order")
public class OrderController {

    private final StateMachineFactory<OrderStatusEnum,OrderEventEnum> stateMachineFactory;

    private final OrderDao orderDao;

    private final StateMachinePersister<OrderStatusEnum,OrderEventEnum,String> stateMachinePersister;


    @PostMapping(value = "/create")
    public WebResponse<String> createOrder(@RequestParam("customerId") String customerId,
                                           @RequestParam("productId") String productId) {
        StateMachine<OrderStatusEnum, OrderEventEnum> stateMachine = stateMachineFactory.getStateMachine(OrderStatusStateMachineConfig.STATE_MACHINE);
        String id = this.getKey(customerId, productId);
        orderDao.createOrder(id);
        try {
            stateMachinePersister.persist(stateMachine,id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return WebResponse.success("创建订单成功");
    }

    @PostMapping(value = "/pay")
    public WebResponse<String> payOrder(@RequestParam("customerId") String customerId,
                                        @RequestParam("productId") String productId){
        String id = this.getKey(customerId, productId);
        Message message = MessageBuilder.withPayload(OrderEventEnum.PAY)
                .setHeader("customerId", customerId)
                .setHeader("productId", productId)
                .build();
        StateMachine<OrderStatusEnum, OrderEventEnum> stateMachine = this.getStateMachine(id);
        stateMachine.sendEvent(message);
        try {
            stateMachinePersister.persist(stateMachine,id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return WebResponse.success("支付订单成功");
    }

    @PostMapping(value = "/send")
    public WebResponse<String> sendOrder(@RequestParam("customerId") String customerId,
                                         @RequestParam("productId") String productId){
        String id = this.getKey(customerId, productId);
        Message message = MessageBuilder.withPayload(OrderEventEnum.SEND)
                .setHeader("customerId", customerId)
                .setHeader("productId", productId)
                .build();
        StateMachine<OrderStatusEnum, OrderEventEnum> stateMachine = this.getStateMachine(id);
        stateMachine.sendEvent(message);
        try {
            stateMachinePersister.persist(stateMachine,id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return WebResponse.success("支付订单成功");
    }

    @PostMapping(value = "/confirm")
    public WebResponse<String> confirm(@RequestParam("customerId") String customerId,
                                       @RequestParam("productId") String productId){
        String id = this.getKey(customerId, productId);
        Message message = MessageBuilder.withPayload(OrderEventEnum.CONFIRM)
                .setHeader("customerId", customerId)
                .setHeader("productId", productId)
                .build();
        StateMachine<OrderStatusEnum, OrderEventEnum> stateMachine = this.getStateMachine(id);
        stateMachine.sendEvent(message);
        try {
            stateMachinePersister.persist(stateMachine,id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return WebResponse.success("确认订单成功");
    }

    private  StateMachine<OrderStatusEnum, OrderEventEnum> getStateMachine(String id){
        try {
            return stateMachinePersister.restore(stateMachineFactory.getStateMachine(OrderStatusStateMachineConfig.STATE_MACHINE),
                    id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getKey(String customerId,String productId){
        return customerId + ":" + productId;
    }
}
