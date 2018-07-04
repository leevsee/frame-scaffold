package com.leeves.validate.core;

import com.leeves.properties.SecurityProperties;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Description:
 * OncePerRequestFilter只调一次请求的filter
 * <p>
 * Package: com.leeves
 *
 * @author Leeves
 * @version 1.0.0  2018-06-16
 */

public class ValidateCodeFilter extends OncePerRequestFilter implements InitializingBean {


    private AuthenticationFailureHandler authenticationFailureHandler;

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    private Set<String> urls = new HashSet<>();

    private SecurityProperties securityProperties;

    private AntPathMatcher pathMatcher = new AntPathMatcher();

    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();
        if (StringUtils.isNotBlank(securityProperties.getCode().getImage().getUrl())) {
            String[] configUrls = StringUtils.splitByWholeSeparatorPreserveAllTokens(securityProperties.getCode().getImage().getUrl(), ",");
            urls.addAll(Arrays.asList(configUrls));
        }
        urls.add("/authentication/login");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        boolean action = false;
        for (String url : urls) {
            if (pathMatcher.match(url, request.getRequestURI())) {
                action = true;
            }
        }

        if (action) {
            try {
                validate(new ServletWebRequest(request));
            } catch (ValidateCodeException e) {
                authenticationFailureHandler.onAuthenticationFailure(request, response, e);
                return;
            }

        }

        filterChain.doFilter(request, response);

    }

    private void validate(ServletWebRequest servletWebRequest) throws ServletRequestBindingException {
        //从session中取Imagecode
        ImageCode codeInSessioin = (ImageCode) sessionStrategy.getAttribute(servletWebRequest, ValidateCodeProcessor.SESSION_KEY_PREFIX+"IMAGE");
        //从请求中取到验证码
        String codeInRequest = ServletRequestUtils.getStringParameter(servletWebRequest.getRequest(), "imageCode");

        if (StringUtils.isBlank(codeInRequest)) {
            throw new ValidateCodeException("验证码的值不能为空");
        }

        if (codeInSessioin == null) {
            throw new ValidateCodeException("验证码不存在");
        }

        if (codeInSessioin.isExpried()) {
//            sessionStrategy.removeAttribute(servletWebRequest, ValidateController.SESSION_KEY);
            sessionStrategy.removeAttribute(servletWebRequest, ValidateCodeProcessor.SESSION_KEY_PREFIX+"IMAGE");
            throw new ValidateCodeException("验证码已过期");
        }

        if (!StringUtils.equals(codeInSessioin.getCode(), codeInRequest)) {
            throw new ValidateCodeException("验证码不匹配");
        }
        //验证成功，也需把验证码去除
        sessionStrategy.removeAttribute(servletWebRequest, ValidateCodeProcessor.SESSION_KEY_PREFIX+"IMAGE");

    }

    public void setAuthenticationFailureHandler(AuthenticationFailureHandler authenticationFailureHandler) {
        this.authenticationFailureHandler = authenticationFailureHandler;
    }

    public SecurityProperties getSecurityProperties() {
        return securityProperties;
    }

    public void setSecurityProperties(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }
}