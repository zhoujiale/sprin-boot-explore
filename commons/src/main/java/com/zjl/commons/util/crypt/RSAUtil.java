package com.zjl.commons.util.crypt;

import com.zjl.commons.util.log.ErrorLogUtil;
import org.apache.commons.lang3.StringUtils;

import javax.crypto.Cipher;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * @name: RSAUtil
 * @description:
 * @author: zhou
 * @create: 2021-01-10 15:58
 */
public class RSAUtil {

    private static final String RSA = "RSA";

    private static final int SIZE = 512;

    /**
     * @description 获取密钥对
     * @author zhou
     * @created  2021/1/10 16:14
     * @param
     * @return java.security.KeyPair
     **/
    public static KeyPair getKey(){
        try {
            KeyPairGenerator pairGenerator = KeyPairGenerator.getInstance(RSA);
            pairGenerator.initialize(SIZE);
            return pairGenerator.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            ErrorLogUtil.errorLog(e);
            return null;
        }
    }

    /**
     * @description 私钥加密
     * @author zhou
     * @created  2021/1/10 16:37
     * @param rawData 明文
     * @param privateRSAKey rsa私钥
     * @return java.lang.String
     **/
    public static String privateEncrypt(String rawData, String privateRSAKey){
        try {
            PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateRSAKey));
            KeyFactory keyFactory = KeyFactory.getInstance(RSA);
            PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
            Cipher cipher = Cipher.getInstance(RSA);
            cipher.init(Cipher.ENCRYPT_MODE,privateKey);
            byte[] bytes = cipher.doFinal(rawData.getBytes());
            return Base64.getEncoder().encodeToString(bytes);
        }catch (Exception e){
            ErrorLogUtil.errorLog(e);
            return StringUtils.EMPTY;
        }
    }

    /**
     * @description 公钥解密
     * @author zhou
     * @created  2021/1/10 16:40
     * @param enStr 密文
     * @param publicRSAKey 公钥
     * @return java.lang.String
     **/
    public static String publicDecrypt(String enStr,String publicRSAKey){
        try{
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(publicRSAKey));
            KeyFactory keyFactory = KeyFactory.getInstance(RSA);
            PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
            Cipher cipher = Cipher.getInstance(RSA);
            cipher.init(Cipher.DECRYPT_MODE,publicKey);
            byte[] bytes = cipher.doFinal(Base64.getDecoder().decode(enStr));
            return new String(bytes);
        }catch (Exception e){
            ErrorLogUtil.errorLog(e);
            return StringUtils.EMPTY;
        }
    }

    /**
     * @description 公钥加密
     * @author zhou
     * @created  2021/1/10 16:48
     * @param rawData 明文
     * @param publicRSAKey RSA公钥
     * @return java.lang.String
     **/
    public static String publicEncrypt(String rawData,String publicRSAKey){
        try {
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(publicRSAKey));
            KeyFactory keyFactory = KeyFactory.getInstance(RSA);
            PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
            Cipher cipher = Cipher.getInstance(RSA);
            cipher.init(Cipher.ENCRYPT_MODE,publicKey);
            byte[] bytes = cipher.doFinal(rawData.getBytes());
            return Base64.getEncoder().encodeToString(bytes);
        }catch (Exception e){
            ErrorLogUtil.errorLog(e);
            return StringUtils.EMPTY;
        }
    }

    /**
     * @description 私钥解密
     * @author zhou
     * @created  2021/1/10 16:51
     * @param enStr 密文
     * @param privateRSAKey RSA私钥
     * @return java.lang.String
     **/
    public static String privateDecrypt(String enStr,String privateRSAKey){
        try {
            PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateRSAKey));
            KeyFactory keyFactory = KeyFactory.getInstance(RSA);
            PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
            Cipher cipher = Cipher.getInstance(RSA);
            cipher.init(Cipher.DECRYPT_MODE,privateKey);
            byte[] bytes = cipher.doFinal(Base64.getDecoder().decode(enStr));
            return new String(bytes);
        }catch (Exception e){
            ErrorLogUtil.errorLog(e);
            return StringUtils.EMPTY;
        }
    }

    public static void main(String[] args) {
        String s = "123456";
        KeyPair key = getKey();
        System.out.println("公钥:"+Base64.getEncoder().encodeToString(key.getPublic().getEncoded()));
        System.out.println("私钥:"+Base64.getEncoder().encodeToString(key.getPrivate().getEncoded()));
        String publicEncrypt = publicEncrypt(s, Base64.getEncoder().encodeToString(key.getPublic().getEncoded()));
        System.out.println("公钥加密:"+publicEncrypt);
        String privateDecrypt = privateDecrypt(publicEncrypt, Base64.getEncoder().encodeToString(key.getPrivate().getEncoded()));
        System.out.println("私钥解密:"+privateDecrypt);
    }
}
