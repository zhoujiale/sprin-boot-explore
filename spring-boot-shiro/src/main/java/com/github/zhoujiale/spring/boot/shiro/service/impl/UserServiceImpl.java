package com.github.zhoujiale.spring.boot.shiro.service.impl;

import com.github.zhoujiale.commons.util.log.ErrorLogUtil;
import com.github.zhoujiale.spring.boot.shiro.dao.RoleDao;
import com.github.zhoujiale.spring.boot.shiro.dao.ShiroUserDao;
import com.github.zhoujiale.spring.boot.shiro.domian.bo.LoginParam;
import com.github.zhoujiale.spring.boot.shiro.domian.bo.PasswordParam;
import com.github.zhoujiale.spring.boot.shiro.domian.bo.UserParam;
import com.github.zhoujiale.spring.boot.shiro.domian.vo.UserVO;
import com.github.zhoujiale.spring.boot.shiro.error.ServiceErrorEnum;
import com.github.zhoujiale.spring.boot.shiro.error.ServiceErrorException;
import com.github.zhoujiale.spring.boot.shiro.model.PermissionPO;
import com.github.zhoujiale.spring.boot.shiro.model.RolePO;
import com.github.zhoujiale.spring.boot.shiro.model.ShiroUserPO;
import com.github.zhoujiale.spring.boot.shiro.service.UserService;
import com.github.zhoujiale.spring.boot.shiro.util.PasswordHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.PasswordMatcher;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @name: UserServiceImpl
 * @description:
 * @author: zhou
 * @create: 2020-10-08 10:47
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private ShiroUserDao shiroUserDao;
    @Autowired
    private PasswordHelper passwordHelper;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private PasswordMatcher passwordMatcher;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public ShiroUserPO addUser(UserParam userParam) {
        ShiroUserPO shiroUserPO = UserParam.covertByUserParam(userParam);
        // String salt = passwordHelper.getSalt();
        // String encryptPassword = passwordHelper.encryptPassword(shiroUserPO.getUserPassword(), salt);
        String encryptPassword = passwordMatcher.getPasswordService().encryptPassword(shiroUserPO.getUserPassword());
        shiroUserPO.setUserPassword(encryptPassword);
        // shiroUserPO.setSalt(salt);
        shiroUserPO = shiroUserDao.addUser(shiroUserPO);
        shiroUserDao.addUserRole(shiroUserPO.getUserId(),userParam.getRoleIdList());
        return shiroUserPO;
    }

    @Override
    public UserVO login(LoginParam loginParam) {
        Subject currentUser = SecurityUtils.getSubject();
        //shiro认证
        shiroLogin(loginParam,currentUser);
        ShiroUserPO userByName = shiroUserDao.getUserByName(loginParam.getUserName());
        List<RolePO> rolePOList = shiroUserDao.getUserRoleByUserId(userByName.getUserId());
        List<PermissionPO> permissionPOList = roleDao.getPermissionByRoleIdS(rolePOList.stream()
                .map(RolePO::getRoleId).collect(Collectors.toList()));
        UserVO userVO = UserVO.covertToUserVO(userByName,rolePOList,permissionPOList);
        return userVO;
    }

    @Override
    public void modifyPassword(PasswordParam passwordParam) {
        ShiroUserPO userByName = shiroUserDao.getUserByName(passwordParam.getUserName());
        if (null == userByName){
            log.error("用户不存在");
            throw new ServiceErrorException(ServiceErrorEnum.UN_KNOWN_ACCOUNT);
        }
        // String encryptPassword = passwordHelper.encryptPassword(passwordParam.getPassword(), userByName.getSalt());
        if (!passwordMatcher.getPasswordService().passwordsMatch(passwordParam.getPassword(),userByName.getUserPassword())){
            log.error("账户密码错误");
            throw new ServiceErrorException(ServiceErrorEnum.ERROR_CREDENTIALS);
        }
        // String newPassword = passwordHelper.encryptPassword(passwordParam.getNewPassword(), userByName.getSalt());
        String newPassword = passwordMatcher.getPasswordService().encryptPassword(passwordParam.getNewPassword());
        userByName.setUserPassword(newPassword);
        shiroUserDao.modifyPassword(userByName);
    }

    @Override
    public void logout() {
        SecurityUtils.getSubject().logout();
    }

    /**
     * @description 处理shiro登录
     * @author zhou       
     * @created  2020/10/12 0:08
     * @param 
     * @return void
     **/
    private void shiroLogin(LoginParam loginParam, Subject currentUser){
        UsernamePasswordToken token = new UsernamePasswordToken(loginParam.getUserName(),loginParam.getPassword());
        try {
            currentUser.login(token);
        }catch (UnknownAccountException ua){
            ErrorLogUtil.errorLog(ua);
            throw new ServiceErrorException(ServiceErrorEnum.UN_KNOWN_ACCOUNT);
        }catch (IncorrectCredentialsException ice){
            ErrorLogUtil.errorLog(ice);
            throw new ServiceErrorException(ServiceErrorEnum.ERROR_CREDENTIALS);
        }catch (LockedAccountException le){
            ErrorLogUtil.errorLog(le);
            throw new ServiceErrorException(ServiceErrorEnum.LOCK_ACCOUNT);
        }catch (AuthenticationException ae){
            ErrorLogUtil.errorLog(ae);
            throw new ServiceErrorException(ServiceErrorEnum.AUTH_ERROR);
        }
    }
}
