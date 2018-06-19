package com.leeves.browser.demo.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.leeves.browser.demo.dto.User;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;

/**
 * Description: TODO
 * Package: com.leeves.demo.controller
 *
 * @author Leeves
 * @version 1.0.0  2018-06-15
 */
@Slf4j
@Controller
public class TestController {

    /**
     * JSONView 注解测试
     */
    @GetMapping("/jsonViewTest")
    @JsonView(User.UserSimpleView.class)
    @ResponseBody
    public User jsonViewTest(){
        User user = new User();
        user.setName("leeves");
        user.setPwd("pwd");
        user.setPhone("13888888888");
        return user;
    }

    /**
     * 获得认证后所有的认证信息
     */
    @GetMapping("/authentication/authentication")
    @ResponseBody
    public Object getAuthentication(Authentication authentication) {
//        return SecurityContextHolder.getContext().getAuthentication();
        return authentication;
    }

    /**
     * 获得认证后的用户信息
     */
    @GetMapping("/authentication/userDetail")
    @ResponseBody
    public Object getAuthenticationUserDetails(@AuthenticationPrincipal UserDetails userDetails) {
        return userDetails;
    }

    /**
     * 运行时异常错误测试
     */
    @GetMapping("/exceptionTest")
    @ResponseBody
    public User exceptionTest(){
        throw new RuntimeException("运行时异常错误测试");
    }
}