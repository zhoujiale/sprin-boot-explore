package com.github.zhoujiale.spring.boot.shiro.service;

import com.github.zhoujiale.spring.boot.shiro.domian.bo.LoginParam;
import com.github.zhoujiale.spring.boot.shiro.domian.bo.PasswordParam;
import com.github.zhoujiale.spring.boot.shiro.domian.bo.UserParam;
import com.github.zhoujiale.spring.boot.shiro.domian.vo.UserVO;
import com.github.zhoujiale.spring.boot.shiro.model.ShiroUserPO;

/**
 * @Auther: zhou
 * @Date: 2020-10-08 10:46
 * @Description:
 */
public interface UserService {

    /**
     * @description 添加用户
     * @author zhou
     * @created  2020/10/8 10:52
     * @param userParam 用户参数
     * @return com.github.zhoujiale.spring_boot_shiro.model.ShiroUserPO
     **/
    ShiroUserPO addUser(UserParam userParam);

    /** 
     * @description 登录 
     * @author zhou       
     * @created  2020/10/11 18:20
     * @param 
     * @return com.github.zhoujiale.spring_boot_shiro.domian.vo.UserVO
     **/
    UserVO login(LoginParam loginParam);

    /** 
     * @description 修改密码 
     * @author zhou       
     * @created  2020/10/13 23:15
     * @param 
     * @return void
     **/
    void modifyPassword(PasswordParam passwordParam);
    
    /** 
     * @description 退出登录 
     * @author zhou       
     * @created  2021/1/10 22:30
     * @param 
     * @return void
     **/
    void logout();
}
