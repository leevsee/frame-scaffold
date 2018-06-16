package com.leeves.browser.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leeves.properties.LoginType;
import com.leeves.properties.SecurityProperties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Description: 登陆成功处理
 * 可以 implements AuthenticationSuccessHandler
 * SavedRequestAwareAuthenticationSuccessHandler是spring默认的成功处理类
 *
 * Package: com.leeves.browser.authentication
 *
 * @author Leeves
 * @version 1.0.0  2018-06-15
 */
@Component
public class FrameAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SecurityProperties securityProperties;

    /**
     * Authentication包含了所有的登陆相关信息，不同的方法，得到不同的登陆信息
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        //如果是配置json则返回json，否则自动跳转回原页面
        if (LoginType.JSON.equals(securityProperties.getBrowser().getLoginType())) {
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(authentication));
        } else {
            super.onAuthenticationSuccess(request,response,authentication);
        }


    }

}