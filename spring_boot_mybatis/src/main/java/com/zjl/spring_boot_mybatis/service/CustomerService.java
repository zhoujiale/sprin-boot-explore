package com.zjl.spring_boot_mybatis.service;

import com.zjl.spring_boot_mybatis.model.CustomerPO;

/**
 * @Auther: zhou
 * @Date: 2021-02-17 11:53
 * @Description:
 */
public interface CustomerService {
    
    /** 
     * @description 添加用户 
     * @author zhou       
     * @created  2021/2/17 11:54
     * @param customerPO
     * @return int
     **/
    int addCustomer(CustomerPO customerPO);
}
