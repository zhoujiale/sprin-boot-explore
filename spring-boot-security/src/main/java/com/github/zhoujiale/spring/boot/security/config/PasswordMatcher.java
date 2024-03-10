package com.github.zhoujiale.spring.boot.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author zhou
 * @version 1.0
 * @className PasswordMatcher
 * @description
 * @date 2021/01/21 19:31
 **/
@Component
public class PasswordMatcher {

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    /**
     * @description 加密
     * @author zhou
     * @create 2021/1/21 19:33 
     * @param dataRaw 原文
     * @return java.lang.String
     **/
    public String encryptCode(String dataRaw){
        return passwordEncoder.encode(dataRaw);
    }

    /**
     * @description 判断密码是否正确
     * @author zhou
     * @create 2021/1/21 19:34
     * @param dataRaw 明文
     * @param encrypt 密文
     * @return boolean
     **/
    public boolean matcherCode(String dataRaw,String encrypt){
        return passwordEncoder.matches(dataRaw,encrypt);
    }
}
