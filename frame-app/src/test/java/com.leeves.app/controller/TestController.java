package com.leeves.app.controller;

import com.leeves.app.dto.User;
import com.leeves.fastjson.annotation.JSONP;
import com.leeves.fastjson.annotation.SerializeField;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import lombok.extern.slf4j.Slf4j;

/**
 * Description: 测试类
 * Package: com.leeves.app.controller
 *
 * @author Leeves
 * @date 2018-05-06
 */
@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/fastjson")
    @SerializeField(clazz = User.class, excludes = {"name"})
    public User JsonTest() {
        User user = new User();
        user.setName("lllllllll");
        user.setPwd("88888888888");
        return user;
    }

    @PostMapping("/valid")
    @SerializeField(clazz = User.class, excludes = {"pwd"})
    public User JsonRTest(@Valid @RequestBody User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.info("bindingResult.getFieldError().getDefaultMessage():" + bindingResult.getFieldError().getDefaultMessage());
        }
        log.info("user :" + user);
        return user;
    }

    @GetMapping("/jsonp")
    @JSONP("user")
    @SerializeField(clazz = User.class, excludes = {"name"})
    public User jsonp() {
        User user = new User();
        user.setName("jsonp");
        user.setPwd("jsonp");
        user.setPhone("123");
        return user;
    }
}
