package com.github.zhoujiale.spring.boot.shiro.util;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.lang.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @name: PasswordHelper
 * @description:
 * @author: zhou
 * @create: 2020-10-08 16:11
 */
@Component
public class PasswordHelper {

    @Autowired
    private HashedCredentialsMatcher credentialsMatcher;

    public String getSalt(){
        return UUID.randomUUID().toString().replace("-","");
    }

    public String encryptPassword(String nativePassword,String salt){
        SimpleHash simpleHash = new SimpleHash(credentialsMatcher.getHashAlgorithmName(), nativePassword,
                ByteSource.Util.bytes(salt), credentialsMatcher.getHashIterations());
        return simpleHash.toHex();
    }
}
