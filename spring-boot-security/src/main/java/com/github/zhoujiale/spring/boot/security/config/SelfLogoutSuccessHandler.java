package com.github.zhoujiale.spring.boot.security.config;

import com.alibaba.fastjson2.JSON;
import com.github.zhoujiale.commons.util.response.WebResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author zhou
 * @version 1.0
 * @className SelfLogoutSuccessHandler
 * @description
 * @date 2021/02/05 11:23
 **/
public class SelfLogoutSuccessHandler implements LogoutSuccessHandler {


    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        httpServletResponse.setContentType("application/json");
        httpServletResponse.setCharacterEncoding("UTF-8");
        WebResponse<String> webResponse = WebResponse.success("退出登录成功");
        PrintWriter writer = httpServletResponse.getWriter();
        writer.write(JSON.toJSONString(webResponse));
        writer.flush();
        writer.close();
    }
}
