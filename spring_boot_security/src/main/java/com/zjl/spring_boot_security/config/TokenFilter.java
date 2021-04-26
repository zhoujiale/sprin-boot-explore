package com.zjl.spring_boot_security.config;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.zjl.commons.util.log.ErrorLogUtil;
import com.zjl.spring_boot_security.error.ServiceErrorEnum;
import com.zjl.spring_boot_security.error.ServiceErrorException;
import com.zjl.spring_boot_security.service.impl.SelfUserDetailService;
import com.zjl.spring_boot_security.utlil.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * @author zhou
 * @version 1.0
 * @className TokenFilter
 * @description
 * @date 2021/04/20 22:02
 **/
@Slf4j
@Component
public class TokenFilter extends OncePerRequestFilter {

    @Autowired
    private SelfUserDetailService selfUserDetailService;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String remark = httpServletRequest.getHeader(RequestConstant.REMARK);
        if(StringUtils.isBlank(remark)){
            filterChain.doFilter(httpServletRequest,httpServletResponse);
        }else {
            String token = httpServletRequest.getHeader(RequestConstant.TOKEN);
            this.authHandler(token,httpServletRequest);
            filterChain.doFilter(httpServletRequest,httpServletResponse);
        }
    }

    private void authHandler(String token,HttpServletRequest request){
        if (StringUtils.isBlank(token)){
            log.error("缺少令牌token");
            throw new ServiceErrorException(ServiceErrorEnum.TOKEN_EMPTY);
        }
        try {
            DecodedJWT parse = JWTUtil.parse(token);
            Date expiresAt = parse.getExpiresAt();
            JWTUtil.checkExpire(expiresAt);
            parse = JWTUtil.verify(token);
            //获取载荷
            List<String> audience = parse.getAudience();
            if(CollectionUtils.isNotEmpty(audience) && audience.size() >1){
                String name = audience.get(1);
                this.authUser(name,request);
            }else {
                log.error("jwt信息不完整");
                throw new ServiceErrorException(ServiceErrorEnum.JWT_COMPLETE);
            }
        }catch (JWTDecodeException jd){
            ErrorLogUtil.errorLog(jd);
            throw new ServiceErrorException(ServiceErrorEnum.JWT_PARSE_ERROR);
        }catch (JWTVerificationException jv){
            ErrorLogUtil.errorLog(jv);
            throw new ServiceErrorException(ServiceErrorEnum.JWT_VERIFY_ERROR);
        }
    }
    
    /**
     * @description 手动授权
     * @author zhou
     * @create 2021/4/23 12:11 
     * @param 
     * @return void
     **/
    private void authUser(String username,HttpServletRequest request){
        //加载主体
        UserDetails userDetails = selfUserDetailService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, null, userDetails.getAuthorities());
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }
}
