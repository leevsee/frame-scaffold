package com.leeves.properties;

import lombok.Data;

/**
 * Description:
 * Package: com.leeves.properties
 *
 * @author Leeves
 * @version 1.0.0  2018-06-15
 */
@Data
public class BrowserProperties {

    /**
     * 默认登陆配置
     */
    private String loginPage = SecurityConstants.DEFAULT_LOGIN_PAGE_URL;

    private LoginType loginType = LoginType.JSON;

    private String signUpUrl = "/sginIn.html";

    private int rememberMeSec = 3600;

}