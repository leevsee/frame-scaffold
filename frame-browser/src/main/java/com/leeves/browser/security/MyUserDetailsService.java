package com.leeves.browser.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

/**
 * Description:
 * Package: com.leeves.browser.security
 *
 * @author Leeves
 * @version 1.0.0  2018-06-10
 */
@Slf4j
@Component
public class MyUserDetailsService implements UserDetailsService {

/*    @Autowired
    private PasswordEncoder mPasswordEncoder;*/

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("spring security username:" + username);
        //TODO: 根据用户名查找数据库用户信息
        String userRoles = "admin";
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encode = bCryptPasswordEncoder.encode("123");
        log.info("spring security encode:" + encode);
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(userRoles);
        return new User(username, encode, grantedAuthorities);
    }

}
