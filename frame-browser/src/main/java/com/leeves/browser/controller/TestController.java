package com.leeves.browser.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.leeves.browser.dto.User;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

/**
 * Description:
 * Package: com.leeves.browser.controller
 *
 * @author Leeves
 * @date 2018-06-08
 */
@Slf4j
@RestController
public class TestController {

    /**
     * JSONView 注解测试
     */
    @GetMapping("/jsonViewTest")
    @JsonView(User.UserSimpleView.class)
    public User jsonViewTest(){
        User user = new User();
        user.setName("leeves");
        user.setPwd("pwd");
        user.setPhone("13888888888");
        return user;
    }

    /**
     * 运行时异常错误测试
     */
    @GetMapping("/exceptionTest")
    public User exceptionTest(){
        throw new RuntimeException("运行时异常错误测试");
    }
}
