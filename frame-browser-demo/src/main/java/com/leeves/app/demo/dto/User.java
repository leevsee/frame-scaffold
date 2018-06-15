package com.leeves.app.demo.dto;

import com.fasterxml.jackson.annotation.JsonView;

import lombok.Data;

/**
 * Description: TODO
 * Package: com.leeves.demo.dto
 *
 * @author Leeves
 * @version 1.0.0  2018-06-15
 */
@Data
public class User {
    public interface UserSimpleView{}

    public interface UserAllView{}

    @JsonView(UserSimpleView.class)
    private String name;
    @JsonView(UserAllView.class)
    private String pwd;
    @JsonView(UserSimpleView.class)
    private String phone;
}