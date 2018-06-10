package com.leeves.browser.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.Data;

/**
 * Description:
 * Package: com.leeves.browser.dto
 *
 * @author Leeves
 * @date 2018-06-08
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
    @JsonFormat()
    private String phone;
}
