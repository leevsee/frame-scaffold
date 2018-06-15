package com.leeves.app.demo.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.leeves.app.demo.dto.User;
import com.leeves.demo.dto.User;

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

//    @GetMapping("/login")
//    public String login() {
//        return "login";
//    }

    /**
     * 运行时异常错误测试
     */
    @GetMapping("/exceptionTest")
    @ResponseBody
    public User exceptionTest(){
        throw new RuntimeException("运行时异常错误测试");
    }
}