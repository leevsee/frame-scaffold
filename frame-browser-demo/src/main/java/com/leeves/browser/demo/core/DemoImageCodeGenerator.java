package com.leeves.browser.demo.core;

import com.leeves.validate.core.ImageCode;
import com.leeves.validate.core.ValidateCodeGenerator;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * Description: TODO
 * Package: com.leeves.browser.demo.core
 *
 * @author Leeves
 * @version 1.0.0  2018-06-17
 */
//@Component("imageCodeGenerator")
public class DemoImageCodeGenerator implements ValidateCodeGenerator {

    @Override
    public ImageCode generate(ServletWebRequest request) {
        return null;
    }

}