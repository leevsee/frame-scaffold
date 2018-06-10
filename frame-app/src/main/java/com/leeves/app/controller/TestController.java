package com.leeves.app.controller;

import com.leeves.app.dto.User;
import com.leeves.app.model.valid.FirstGroup;
import com.leeves.base.R;
import com.leeves.fastjson.annotation.JSONP;
import com.leeves.fastjson.annotation.SerializeField;

import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.groups.Default;

import lombok.extern.slf4j.Slf4j;

/**
 * Description: 测试演示controller
 * Package: com.leeves.app.controller
 *
 * @author Leeves
 * @date 2018-05-06
 */
@Slf4j
@RestController
public class TestController {

    /**
     * FastJson排除类
     */
    @GetMapping("/test")
    @SerializeField(clazz = User.class, excludes = {"name"})
    public User jsonTest() {
        User user = new User();
        user.setName("lllllllll");
        user.setPwd("88888888888");
        return user;
    }

    /**
     * aop验证返回，分组验证
     */
    @PostMapping("/test")
    @SerializeField(clazz = User.class, excludes = {"pwd"})
    public R jsonRTest(@Validated(value = {FirstGroup.class, Default.class}) @RequestBody User user, BindingResult bindingResult) {
        log.info("user :" + user);
        return R.ok(user);
    }

    /**
     * FastJson Jsonp实现
     */
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

    /**
     * 500服务器错误
     */
    @GetMapping("/testErr")
    public String errTest() {
        throw new RuntimeException("500服务器错误");
    }

}
