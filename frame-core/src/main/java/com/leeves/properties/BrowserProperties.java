package com.leeves.properties;

/**
 * Description:
 * Package: com.leeves.properties
 *
 * @author Leeves
 * @version 1.0.0  2018-06-15
 */
public class BrowserProperties {

    /**
     * 默认登陆配置
     */
    private String loginPage = "/sginIn.html";

    private LoginType loginType = LoginType.JSON;

    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }

    public LoginType getLoginType() {
        return loginType;
    }

    public void setLoginType(LoginType loginType) {
        this.loginType = loginType;
    }
}