package com.zjl.spring_boot_mybatis.service.impl;

import com.zjl.spring_boot_mybatis.model.CustomerPO;
import com.zjl.spring_boot_mybatis.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

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