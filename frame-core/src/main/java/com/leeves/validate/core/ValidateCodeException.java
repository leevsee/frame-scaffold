package com.leeves.validate.core;

import org.springframework.security.core.AuthenticationException;

/**
 * Description:
 * Package: com.leeves.validate
 *
 * @author Leeves
 * @version 1.0.0  2018-06-16
 */
public class ValidateCodeException extends AuthenticationException {

    public ValidateCodeException(String msg) {
        super(msg);
    }

}