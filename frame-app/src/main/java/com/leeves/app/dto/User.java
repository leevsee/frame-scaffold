package com.leeves.app.dto;

import com.leeves.app.annotation.CheckPhone;
import com.leeves.app.model.valid.FirstGroup;
import com.leeves.app.model.valid.SecondGroup;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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

    @Valid
    @NotBlank(message = "用户名不能为空", groups = FirstGroup.class)
    private String name;

    @Valid
    @NotBlank(message = "用户密码不能为空", groups = SecondGroup.class)
    private String pwd;

    @CheckPhone(message = "不是正确的电话号码")
    @NotBlank(message = "电话号码不能为空")
    private String phone;

}
