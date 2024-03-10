package com.github.zhoujiale.spring.boot.shiro.config;

import com.alibaba.fastjson2.JSON;
import com.github.zhoujiale.commons.util.response.WebResponse;
import com.github.zhoujiale.spring.boot.shiro.error.ServiceErrorEnum;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @name: CorsAuthFilter
 * @description: 自定义登录验证过滤器
 * @author: zhou
 * @create: 2021-01-10 22:47
 */
public class CorsAuthFilter extends FormAuthenticationFilter {

    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest servletRequest = WebUtils.toHttp(request);
        HttpServletResponse servletResponse = WebUtils.toHttp(response);
        CorsFilter.setHeader(servletRequest,servletResponse);
        if (servletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            servletResponse.setStatus(HttpStatus.OK.value());
            return false;
        }
        return super.preHandle(request, response);
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        if (request instanceof HttpServletRequest &&
                WebUtils.toHttp(request).getMethod().equals(HttpMethod.OPTIONS.name())){
            return true;
        }
        return super.isAccessAllowed(request, response, mappedValue);
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest servletRequest = WebUtils.toHttp(request);
        HttpServletResponse servletResponse = WebUtils.toHttp(response);
        servletResponse.setHeader("Access-Control-Allow-Origin", servletRequest.getHeader("Origin"));
        servletResponse.setHeader("Access-Control-Allow-Credentials", "true");
        servletResponse.setStatus(HttpStatus.OK.value());
        servletResponse.setCharacterEncoding("UTF-8");
        servletResponse.setContentType("application/json");
        PrintWriter printWriter = servletResponse.getWriter();
        WebResponse<Object> fail = WebResponse.fail(ServiceErrorEnum.NEED_LOGIN.getErrCode(),
                ServiceErrorEnum.NEED_LOGIN.getErrMsg());
        printWriter.write(JSON.toJSONString(fail));
        printWriter.close();
        return false;
    }


}
