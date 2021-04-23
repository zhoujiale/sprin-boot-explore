package com.zjl.spring_boot_security.utlil;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.zjl.commons.util.date.DateUtil;
import com.zjl.spring_boot_security.auth0.JwtKey;
import com.zjl.spring_boot_security.error.ServiceErrorEnum;
import com.zjl.spring_boot_security.error.ServiceErrorException;
import com.zjl.spring_boot_security.model.SecurityUserPO;
import lombok.extern.slf4j.Slf4j;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author zhou
 * @version 1.0
 * @className JWTUtil
 * @description
 * @date 2021/04/20 22:17
 **/
@Slf4j
public class JWTUtil {

    private static final String ADMIN = "JWT";

    private static final long EXPIRE_TIME = 24L;

    /**
     * @description 生成token
     * @author zhou
     * @create 2021/4/20 22:20
     * @param
     * @return java.lang.String
     **/
    public static String getJwtToken(SecurityUserPO securityUserPO){
        String secret = JwtKey.KEY;
        Algorithm algorithm = Algorithm.HMAC256(secret);
        return JWT.create()
                .withIssuer(ADMIN)
                .withIssuedAt(DateUtil.localDateTimeToDate(LocalDateTime.now()))
                .withAudience(
                        String.valueOf(securityUserPO.getUserId()),
                        securityUserPO.getUsername(),
                        String.valueOf(securityUserPO.getDeptId())
                )
                .withExpiresAt(getExpireDate(LocalDateTime.now(),EXPIRE_TIME))
                .sign(algorithm);
    }

    /**
     * @description 解析jwt
     * @author zhou
     * @create 2021/4/22 12:28
     * @param token
     * @return DecodedJWT
     **/
    public static DecodedJWT parse(String token) throws JWTDecodeException {
        return JWT.decode(token);
    }

    /**
     * @description 验证jwt
     * @author zhou
     * @create 2021/4/22 12:28
     * @param token
     * @return com.auth0.jwt.interfaces.DecodedJWT
     **/
    public static DecodedJWT verify(String token) throws JWTVerificationException {
        String secret = JwtKey.KEY;
        Algorithm algorithm = Algorithm.HMAC256(secret);
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer(ADMIN)
                .build();
        return verifier.verify(token);
    }
    /**
     * @description 获取过期时间
     * @author zhou
     * @create 2021/4/22 11:48 
     * @param
     * @return java.util.Date
     **/
    private static Date getExpireDate(LocalDateTime localDateTime,long expireTime){
        LocalDateTime expireDate = localDateTime.plusHours(expireTime);
        ZoneId zoneId = ZoneId.systemDefault();
        Instant instant = expireDate.atZone(zoneId).toInstant();
        return Date.from(instant);
    }

    /**
     * @description 检查过期
     * @author zhou
     * @create 2021/4/22 13:28
     * @param expireAt 过期时间
     * @return void
     **/
    public static void checkExpire(Date expireAt) throws ServiceErrorException {
        if(expireAt.before(DateUtil.localDateTimeToDate(LocalDateTime.now()))){
            log.warn("jwt已过期");
            throw new ServiceErrorException(ServiceErrorEnum.JWT_EXPIRE);
        }
    }
}
