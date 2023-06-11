package com.yxy.resume.handler;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author YeXingyi
 * @version 1.0
 * @date 2023/6/11 12:59
 */
@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        // 设置错误消息
        String errorMessage = "登录失败，请检查用户名和密码";
        request.getSession().setAttribute("errorMessage", errorMessage);

        // 设置响应的状态码为 401 （表示未经授权）
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        // 将错误消息作为响应体
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.write(errorMessage);
        out.flush();
        out.close();
    }
}
