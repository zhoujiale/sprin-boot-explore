package com.zjl.spring_boot_shiro.service.impl;

import com.zjl.commons.util.log.ErrorLogUtil;
import com.zjl.spring_boot_shiro.dao.RoleDao;
import com.zjl.spring_boot_shiro.domian.bo.RoleBO;
import com.zjl.spring_boot_shiro.error.ServiceErrorEnum;
import com.zjl.spring_boot_shiro.error.ServiceErrorException;
import com.zjl.spring_boot_shiro.model.RolePO;
import com.zjl.spring_boot_shiro.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

/**
 * @author zhou
 * @version 1.0
 * @className RoleServiceImp
 * @description
 * @date 2021/01/18 18:18
 **/
@Slf4j
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;


    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public RolePO addRole(RoleBO roleBO) {
        try {
            return roleDao.add(roleBO);
        }catch (RuntimeException e){
            ErrorLogUtil.errorLog(e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new ServiceErrorException(ServiceErrorEnum.ADD_ROLE_ERROR);
        }
    }
}
