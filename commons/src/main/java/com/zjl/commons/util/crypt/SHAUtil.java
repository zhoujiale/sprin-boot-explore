package com.zjl.commons.util.crypt;

import com.zjl.commons.util.log.ErrorLogUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author zhou
 * @version 1.0
 * @className SHAUtil
 * @description sha工具
 * @date 2021/01/08 19:56
 **/
@Slf4j
public class SHAUtil {

    private static final String SHA1 = "SHA";

    private static final String SHA256 = "SHA-256";

    private static final String SHA512 = "SHA-512";

    /**
     * @description sha加密
     * @author zhou
     * @created  2021/1/9 19:42
     * @param rawData 明文
     * @param algorithm 算法
     * @return java.lang.String
     **/
    public static String encrypt(String rawData,final String algorithm){
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
            byte[] bytes = messageDigest.digest(rawData.getBytes());
            return DatatypeConverter.printHexBinary(bytes).toLowerCase();
        }catch (NoSuchAlgorithmException e){
            ErrorLogUtil.errorLog(e);
            return StringUtils.EMPTY;
        }
    }

}
