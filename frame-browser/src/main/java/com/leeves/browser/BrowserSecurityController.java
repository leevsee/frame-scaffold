package com.leeves.browser;

import com.leeves.browser.support.SimpleResponse;
import com.leeves.properties.SecurityProperties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Description: 登陆跳转类
 * Package: com.leeves.browser
 *
 * @author Leeves
 * @version 1.0.0  2018-06-15
 */
@RestController
public class BrowserSecurityController {

    /**
     * http请求缓存到此类
     */
    private RequestCache mRequestCache = new HttpSessionRequestCache();

    private RedirectStrategy mRedirectStrategy = new DefaultRedirectStrategy();

    @Autowired
    private SecurityProperties securityProperties;

    /**
     * 身份认证跳转处理
     */
    @RequestMapping("/authentication/require")
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public SimpleResponse requireAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SavedRequest cacheRequest = mRequestCache.getRequest(request, response);
        if (cacheRequest != null) {
            String targetUrl = cacheRequest.getRedirectUrl();
            if (StringUtils.endsWithIgnoreCase(targetUrl, ".html")) {
                mRedirectStrategy.sendRedirect(request, response, securityProperties.getBrowser().getLoginPage());
            }
        }

        return new SimpleResponse("访问的服务器需要认证，请引导用户到登陆页面");
    }

}