package com.zjl.spring_boot_shiro.config;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

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
    public SessionManager sessionManager(RedisSessionDAO redisSessionDAO,RedisCacheManager redisCacheManager){
        SelfSessionManager sessionManager = new SelfSessionManager();
        sessionManager.setSessionDAO(redisSessionDAO);
        sessionManager.setCacheManager(redisCacheManager);
        return sessionManager;
    }

    @Bean
    public RedisCacheManager redisCacheManager(RedisManager redisManager){
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager);
        redisCacheManager.setPrincipalIdFieldName("userId");
        return redisCacheManager;
    }

    @Bean
    public RedisSessionDAO redisSessionDAO(RedisManager redisManager){
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager);
        return redisSessionDAO;
    }

    /** 
     * @description
     * @author zhou       
     * @created  2020/10/11 14:23
     * @param 
     * @return org.crazycake.shiro.RedisManager
     **/
    @Bean
    public RedisManager redisManager(@Value("${spring.redis.host}") String host,@Value("${spring.redis.port}") String port,
                                     @Value("${spring.redis.password}") String password){
        RedisManager redisManager = new RedisManager();
        redisManager.setHost(host+":"+port);
        redisManager.setPassword(password);
        return redisManager;
    }


    /**
     * @description shiro安全管理器
     * @author zhou
     * @created  2020/10/7 17:27
     * @param
     * @return org.apache.shiro.mgt.SecurityManager
     **/
    @Bean
    public DefaultWebSecurityManager securityManager(SessionManager sessionManager){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(selfRealm());
        securityManager.setSessionManager(sessionManager);
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
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
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
        selfRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return selfRealm;
    }

    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName(Md5Hash.ALGORITHM_NAME);
        hashedCredentialsMatcher.setHashIterations(2);
        return hashedCredentialsMatcher;
    }

    /** 
     * @description 启用注解
     * @author zhou       
     * @created  2020/10/7 18:23
     * @param 
     * @return org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor
     **/
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }

    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setUsePrefix(true);
        return defaultAdvisorAutoProxyCreator;
    }
}
