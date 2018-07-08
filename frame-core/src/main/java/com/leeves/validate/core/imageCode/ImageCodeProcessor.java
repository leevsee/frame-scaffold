package com.leeves.validate.core.imageCode;

import com.leeves.validate.core.impl.AbstractValidateCodeProcessor;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;

/**
 * Description: TODO
 * Package: com.leeves.validate.core.imageCode
 *
 * @author Leeves
 * @version 1.0.0  2018-07-01
 */
@Component("imageValidateCodeProcessor")
public class ImageCodeProcessor extends AbstractValidateCodeProcessor<ImageCode> {

    /**
     * 发送图像验证码
     */
    @Override
    protected void send(ServletWebRequest request, ImageCode imageCode) throws Exception {
        ImageIO.write(imageCode.getImage(), "JPEG", request.getResponse().getOutputStream());
    }

    @Override
    public void validate(ServletWebRequest servletWebRequest) {

    }
}