package com.leeves.browser;

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
 * Description: TODO
 * Package: com.leeves.browser
 *
 * @author Leeves
 * @version 1.0.0  2018-06-15
 */
@RestController
public class BrowserSecurityController {

    /** http请求缓存到此类 */
    private RequestCache mRequestCache = new HttpSessionRequestCache();

    private RedirectStrategy mRedirectStrategy = new DefaultRedirectStrategy();

    /**
     * 身份认证
     */
    @RequestMapping("/authentication/require")
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public String requireAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SavedRequest cacheRequest = mRequestCache.getRequest(request, response);
        if (cacheRequest !=null){
            String targetUrl = cacheRequest.getRedirectUrl();
            if (!StringUtils.endsWithIgnoreCase(targetUrl,".html")){
                mRedirectStrategy.sendRedirect(request,response,"");
            }
        }

        return null;
    }

}