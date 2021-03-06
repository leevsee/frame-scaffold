package com.leeves.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Description: 主配置类
 * Package: com.leeves.properties
 *
 * @author Leeves
 * @version 1.0.0  2018-06-16
 */
@ConfigurationProperties(prefix = "leeves.frame")
public class SecurityProperties {

    private BrowserProperties browser = new BrowserProperties();
    private ValidateCodeProperites code = new ValidateCodeProperites();

    public BrowserProperties getBrowser() {
        return browser;
    }

    public void setBrowser(BrowserProperties browser) {
        this.browser = browser;
    }

    public ValidateCodeProperites getCode() {
        return code;
    }

    public void setCode(ValidateCodeProperites code) {
        this.code = code;
    }
}