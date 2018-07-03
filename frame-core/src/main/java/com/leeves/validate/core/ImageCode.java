package com.leeves.validate.core;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Description: 图片验证码
 * Package: com.leeves.validate.core
 *
 * @author Leeves
 * @version 1.0.0  2018-06-16
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ImageCode extends ValidateCode {

    private BufferedImage image;

    public ImageCode(BufferedImage image, String code, int expireIn) {
        super(code,expireIn);
        this.image = image;
    }

    public ImageCode(BufferedImage image, String code, LocalDateTime expireTime) {
        super(code,expireTime);
        this.image = image;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

}