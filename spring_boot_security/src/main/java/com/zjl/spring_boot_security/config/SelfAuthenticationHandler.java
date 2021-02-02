package com.zjl.spring_boot_security.config;

import com.alibaba.fastjson.JSON;
import com.zjl.commons.util.response.WebResponse;
import com.zjl.spring_boot_security.error.ServiceErrorEnum;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

/**
 * @author zhou
 * @version 1.0
 * @className SelfAuthenticationHandler
 * @description
 * @date 2021/02/02 14:46
 **/
public class SelfAuthenticationHandler implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        //处理异常
        Object webJson;
        if (e instanceof UsernameNotFoundException) {
            //查询不到账号
            webJson = WebResponse.fail(ServiceErrorEnum.UN_KNOWN_ACCOUNT.getErrCode(), ServiceErrorEnum.UN_KNOWN_ACCOUNT.getErrMsg());
        } else if (e instanceof BadCredentialsException) {
            //账号或密码错误
            webJson = WebResponse.fail(ServiceErrorEnum.ERROR_CREDENTIALS.getErrCode(), ServiceErrorEnum.ERROR_CREDENTIALS.getErrMsg());
        } else if (e instanceof AccountExpiredException) {
            //账号过期
            webJson = WebResponse.fail(ServiceErrorEnum.ACCOUNT_EXPIRE.getErrCode(), ServiceErrorEnum.ACCOUNT_EXPIRE.getErrMsg());
        } else if (e instanceof ProviderNotFoundException) {
            //认证方式不支持
            webJson = WebResponse.fail(ServiceErrorEnum.PROVIDER_NOT_FOUND.getErrCode(), ServiceErrorEnum.PROVIDER_NOT_FOUND.getErrMsg());
        } else if (e instanceof DisabledException) {
            //账号被禁用
            webJson = WebResponse.fail(ServiceErrorEnum.DISABLE_ERROR.getErrCode(), ServiceErrorEnum.DISABLE_ERROR.getErrMsg());
        } else if (e instanceof LockedException) {
            //账号被锁定
            webJson = WebResponse.fail(ServiceErrorEnum.LOCK_ACCOUNT.getErrCode(), ServiceErrorEnum.LOCK_ACCOUNT.getErrMsg());
        } else if (e instanceof CredentialsExpiredException) {
            //凭证过期异常异常
            webJson = WebResponse.fail(ServiceErrorEnum.CREDENTIALS_EXPIRE.getErrCode(), ServiceErrorEnum.CREDENTIALS_EXPIRE.getErrMsg());
        }else if(e instanceof InsufficientAuthenticationException){
            //需要认证
            webJson = WebResponse.fail(ServiceErrorEnum.NEED_LOGIN.getErrCode(),ServiceErrorEnum.NEED_LOGIN.getErrMsg());
        } else {
            //认证服务异常
            webJson = WebResponse.fail(ServiceErrorEnum.AUTH_SERVICE_ERROR.getErrCode(), ServiceErrorEnum.AUTH_SERVICE_ERROR.getErrMsg());
        }
        httpServletResponse.setStatus(HttpStatus.OK.value());
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json");
        Writer writer = httpServletResponse.getWriter();
        String toJSONString = JSON.toJSONString(webJson);
        writer.write(toJSONString);
        writer.flush();
        writer.close();
    }
}
