package com.zjl.spring_boot_mybatis.service.impl;

import com.zjl.spring_boot_mybatis.mapper.CustomerPOMapper;
import com.zjl.spring_boot_mybatis.model.CustomerPO;
import com.zjl.spring_boot_mybatis.service.CustomerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @name: CustomerServiceImpl
 * @description:
 * @author: zhou
 * @create: 2021-02-17 11:53
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    @Resource
    private CustomerPOMapper customerPOMapper;

    @Override
    public int addCustomer(CustomerPO customerPO) {
        return customerPOMapper.insertSelective(customerPO);
    }
}
