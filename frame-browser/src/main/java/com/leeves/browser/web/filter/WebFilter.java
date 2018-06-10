package com.leeves.browser.web.filter;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Description: 过滤器
 * 只能获取request和response的参数，无法得知是哪个controller方法。J2E
 *
 * Package: com.leeves.app.filter
 *
 * @author Leeves
 * @version 1.0.0  2018-06-10
 */
//@Component
public class WebFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("web filter init");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        long startTime = new Date().getTime();
        filterChain.doFilter(request, response);
        long endTime = new Date().getTime();
        System.out.println("web filter doFilter time:" + (endTime - startTime)+"ms");
    }

    @Override
    public void destroy() {
        System.out.println("web filter destroy");
    }

}
