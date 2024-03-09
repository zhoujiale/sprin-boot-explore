package com.zjl.commons.util.crypt;

import com.zjl.commons.util.log.ErrorLogUtil;
import org.apache.commons.lang3.StringUtils;

import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author zhou
 * @version 1.0
 * @className MD5Util
 * @description MD5摘要工具
 * @date 2021/01/08 19:21
 **/
public class MD5Util {

    private static final String MD5 = "MD5";

    /**
     * @description md5加密
     * @author zhou
     * @create 2021/1/8 19:51
     * @param rawData  原始数据
     * @return java.lang.String
     **/
    public static String encrypt(String rawData){
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(MD5);
            byte[] digest = messageDigest.digest(rawData.getBytes(StandardCharsets.UTF_8));
            return DatatypeConverter.printHexBinary(digest).toLowerCase();
        } catch (NoSuchAlgorithmException e) {
            ErrorLogUtil.errorLog(e);
            return StringUtils.EMPTY;
        }
    }

}
