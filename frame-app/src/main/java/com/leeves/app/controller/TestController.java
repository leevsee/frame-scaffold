package com.leeves.app.controller;

import com.leeves.app.dto.User;
import com.leeves.fastjson.annotation.SerializeField;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description: TODO
 * Package: com.leeves.app.controller
 *
 * @author Leeves
 * @date 2018-05-06
 */
@RestController
public class TestController {

    @GetMapping("/test")
    @SerializeField(clazz = User.class,excludes = {"pwd"})
    public User JsonTest(){
        User user = new User();
        user.setName("lllllllll");
        user.setPwd("88888888888");
        return user;
    }
}
