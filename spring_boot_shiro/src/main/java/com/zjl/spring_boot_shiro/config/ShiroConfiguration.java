package com.zjl.spring_boot_shiro.config;

import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

/**
 * @name: ShiroConfiguration
 * @description: shiro 配置
 * @author: zhou
 * @create: 2020-10-07 13:16
 */
@Configuration
public class ShiroConfiguration {
    
    /** 
     * @description 会话管理器
     * @author zhou       
     * @created  2020/10/8 15:58
     * @param 
     * @return org.apache.shiro.session.mgt.SessionManager
     **/
    @Bean
    public SessionManager sessionManager(){
        SelfSessionManager sessionManager = new SelfSessionManager();
        sessionManager.setSessionDAO(redisSessionDAO());
        return sessionManager;
    }

    @Bean
    public RedisSessionDAO redisSessionDAO(){
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        return redisSessionDAO;
    }


    /**
     * @description shiro安全管理器
     * @author zhou
     * @created  2020/10/7 17:27
     * @param
     * @return org.apache.shiro.mgt.SecurityManager
     **/
    @Bean
    public DefaultWebSecurityManager securityManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(selfRealm());
        securityManager.setSessionManager(sessionManager());
        return securityManager;
    }

    /** 
     * @description shiro过滤器配置
     * @author zhou       
     * @created  2020/10/7 17:37
     * @param 
     * @return org.apache.shiro.spring.web.ShiroFilterFactoryBean
     **/
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager());
        LinkedHashMap<String, String> filterChainMap  = new LinkedHashMap<String, String>();
        filterChainMap.put("/swagger-ui/**","anon");
        filterChainMap.put("/webjars/**","anon");
        filterChainMap.put("/swagger-resources/**","anon");
        filterChainMap.put("/v3/**","anon");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainMap);
        return shiroFilterFactoryBean;
    }

    /**
     * @description realm实现
     * @author zhou
     * @created  2020/10/7 18:22
     * @param
     * @return com.zjl.spring_boot_shiro.config.SelfRealm
     **/
    @Bean
    public SelfRealm selfRealm(){
        SelfRealm selfRealm = new SelfRealm();
        return selfRealm;
    }



    /** 
     * @description 启用注解
     * @author zhou       
     * @created  2020/10/7 18:23
     * @param 
     * @return org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor
     **/
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(){
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager());
        return advisor;
    }
}
