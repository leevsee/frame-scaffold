package com.leeves.authentication.mobile;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Description:
 * Package: com.leeves.authentication.mobile
 *
 * @author Leeves
 * @version 1.0.0  2018-07-03
 */
public class SmsCodeAuthenticationProvider implements AuthenticationProvider {

    private UserDetailsService mUserDetailsService;

    /**
     * 认证方法
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        SmsCodeAuthenticationToken authenticationToken = (SmsCodeAuthenticationToken) authentication;
        //具体认证方法
        UserDetails userDetails = mUserDetailsService.loadUserByUsername((String) authenticationToken.getPrincipal());
        if (userDetails == null) {
            throw new InternalAuthenticationServiceException("无法获取到用户信息");
        }
        //认证成功后，设置用户信息和用户权限
        SmsCodeAuthenticationToken authenticationTokenResult = new SmsCodeAuthenticationToken(userDetails, userDetails.getAuthorities());
        //把未认证时，一些request信息放到已认证的token中
        authenticationTokenResult.setDetails(authenticationToken.getDetails());
        return authenticationTokenResult;
    }

    /**
     * 让AuthenticationManager判断是否使用本方法
     */
    @Override
    public boolean supports(Class<?> authentication) {
        //判断传进来的是不是SmsCodeAuthenticationToken类型
        return SmsCodeAuthenticationToken.class.isAssignableFrom(authentication);
    }

    public UserDetailsService getUserDetailsService() {
        return mUserDetailsService;
    }

    public void setUserDetailsService(UserDetailsService userDetailsService) {
        mUserDetailsService = userDetailsService;
    }
}