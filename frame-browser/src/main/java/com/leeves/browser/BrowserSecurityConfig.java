package com.leeves.browser;

import com.leeves.browser.authentication.FrameAuthenticationSuccessHandler;
import com.leeves.properties.SecurityProperties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * Description: url权限配置
 * Package: com.leeves.browser
 *
 * @author Leeves
 * @version 1.0.0  2018-06-15
 */
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    //    @Autowired
//    private MyUserDetailsServiceImpl mMyUserDetailsService;
    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private AuthenticationSuccessHandler frameAuthenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler frameAuthenticationFailureHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage("/authentication/require")
                //usernamePassworAuthenticationFilter类处理该登陆请求
                .loginProcessingUrl("/authentication/login")
                .successHandler(frameAuthenticationSuccessHandler)
                .failureHandler(frameAuthenticationFailureHandler)
//        http.httpBasic()
                .and()
                //下面都是授权配置
                .authorizeRequests()
                //排除认证
                .antMatchers("/authentication/require", securityProperties.getBrowser().getLoginPage()).permitAll()
                //任何请求
                .anyRequest()
                //身份认证
                .authenticated()
                .and()
                .csrf().disable();

/*        http
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .csrf().disable()
                .formLogin()
                .loginPage("/login")
                .failureUrl("/login?error")
                .permitAll();*/
    }

/*    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(mMyUserDetailsService).passwordEncoder(new MyPasswordEncoder());
        auth.userDetailsService(mMyUserDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }*/

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}