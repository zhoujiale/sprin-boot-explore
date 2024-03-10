package com.github.zhoujiale.spring.boot.mybatis.service.impl;

import com.github.zhoujiale.spring.boot.mybatis.model.CustomerPO;
import com.github.zhoujiale.spring.boot.mybatis.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CustomerServiceImplTest {

    @Autowired
    private CustomerService customerService;

    @Test
    void addCustomer() {
        CustomerPO customerPO = new CustomerPO();
        customerPO.setCustomerName("读者1");
        customerService.addCustomer(customerPO);
    }
}