package com.zjl.commons.util.crypt;

import com.zjl.commons.util.log.ErrorLogUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * @name: AESUtil
 * @description: AES算法
 * @author: zhou
 * @create: 2021-01-09 21:43
 */
@Slf4j
public class AESUtil {

    private static final String AES = "AES";

    private static final String PADDING = "AES/ECB/PKCS5Padding";

    /**
     * @description 获取密钥
     * @author zhou
     * @created  2021/1/9 21:45
     * @param
     * @return byte[]
     **/
    public static Key getKey(){
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(AES);
            keyGenerator.init(new SecureRandom());
            SecretKey secretKey = keyGenerator.generateKey();
            byte[] keyByte = secretKey.getEncoded();
            return new SecretKeySpec(keyByte,AES);
        } catch (NoSuchAlgorithmException e) {
            ErrorLogUtil.errorLog(e);
            return null;
        }
    }

    /**
     * @description 加密
     * @author zhou
     * @created  2021/1/9 21:52
     * @param rawData 明文
     * @param key 密钥
     * @return java.lang.String
     **/
    public static String encrypt(String rawData,Key key){
        try {
            Cipher cipher = Cipher.getInstance(PADDING);
            cipher.init(Cipher.ENCRYPT_MODE,key);
            byte[] bytes = cipher.doFinal(rawData.getBytes());
            return Base64.getEncoder().encodeToString(bytes);
        } catch (Exception e) {
            ErrorLogUtil.errorLog(e);
            return StringUtils.EMPTY;
        }
    }

    /**
     * @description 解密
     * @author zhou
     * @created  2021/1/9 22:04
     * @param enStr 密文
     * @param key 密钥
     * @return java.lang.String
     **/
    public static String decrypt(String enStr,Key key){
        try {
            Cipher cipher = Cipher.getInstance(PADDING);
            cipher.init(Cipher.DECRYPT_MODE,key);
            byte[] bytes = cipher.doFinal(Base64.getDecoder().decode(enStr));
            return new String(bytes);
        }catch (Exception e){
            ErrorLogUtil.errorLog(e);
            return StringUtils.EMPTY;
        }
    }


}
