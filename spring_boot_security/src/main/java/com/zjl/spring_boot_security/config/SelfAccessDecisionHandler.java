package com.zjl.spring_boot_security.config;

import com.alibaba.fastjson.JSONObject;
import com.zjl.commons.util.response.WebResponse;
import com.zjl.spring_boot_security.error.ServiceErrorEnum;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

/**
 * @author zhou
 * @version 1.0
 * @className SelfAccessDecisionHandler
 * @description
 * @date 2021/02/02 15:37
 **/
public class SelfAccessDecisionHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setStatus(HttpStatus.OK.value());
        httpServletResponse.setContentType("application/json");
        WebResponse<Object> fail = WebResponse.fail(ServiceErrorEnum.PERMISSION_REFUSE.getErrCode(), ServiceErrorEnum.PERMISSION_REFUSE.getErrMsg());
        Writer writer = httpServletResponse.getWriter();
        String toJSONString = JSONObject.toJSONString(fail);
        writer.write(toJSONString);
        writer.flush();
        writer.close();
    }
}
