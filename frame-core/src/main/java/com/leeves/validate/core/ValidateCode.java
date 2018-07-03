package com.leeves.validate.core;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

import lombok.Data;

/**
 * Description: 短信验证码
 * Package: com.leeves.validate.core
 *
 * @author Leeves
 * @version 1.0.0  2018-06-29
 */
@Data
public class ValidateCode {

    private String code;
    private LocalDateTime expireTime;

    public ValidateCode(String code, int expireIn) {
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
    }

    public ValidateCode(String code, LocalDateTime expireTime) {
        this.code = code;
        this.expireTime = expireTime;
    }

    public boolean isExpried() {
        return LocalDateTime.now().isAfter(expireTime);
    }

}