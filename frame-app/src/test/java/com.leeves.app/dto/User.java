package com.leeves.app.dto;

import com.leeves.app.annotation.CheckPhone;

import lombok.Data;

/**
 * Description: TODO
 * Package: com.leeves.app.dto
 *
 * @author Leeves
 * @date 2018-05-06
 */
@Data
public class User {

    private String name;
    private String pwd;
    @CheckPhone(message = "不是正确的电话号码")
    private String phone;

}
