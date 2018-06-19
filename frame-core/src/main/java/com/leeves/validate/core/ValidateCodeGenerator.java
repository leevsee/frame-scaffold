package com.leeves.validate.core;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * Description: TODO
 * Package: com.leeves.validate.core
 *
 * @author Leeves
 * @version 1.0.0  2018-06-17
 */
public interface ValidateCodeGenerator {

    ImageCode createImageCode(ServletWebRequest request);

}