package com.leeves.browser.security;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Description: 密码方法加密匹配
 * Package: com.leeves.browser.security
 *
 * @author Leeves
 * @version 1.0.0  2018-06-10
 */
public class MyPasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence rawPassword) {
        System.out.println("encode rawPassword:" + rawPassword);
        return rawPassword.toString();
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        System.out.println("matches rawPassword:" + rawPassword);
        System.out.println("matches encodedPassword:" + encodedPassword);
        return encodedPassword.equals(rawPassword.toString());
    }

}
