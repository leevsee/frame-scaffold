package com.leeves.app.demo.filter;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Description: TODO
 * Package: com.leeves.app.filter
 *
 * @author Leeves
 * @version 1.0.0  2018-06-10
 */
@Component
public class WebFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("web filter init");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        long startTime = System.currentTimeMillis();
        filterChain.doFilter(request, response);
        long endTime = System.currentTimeMillis();
        System.out.println("web filter doFilter time:" + (endTime - startTime)+"ms");
    }

    @Override
    public void destroy() {
        System.out.println("web filter destroy");
    }

}
