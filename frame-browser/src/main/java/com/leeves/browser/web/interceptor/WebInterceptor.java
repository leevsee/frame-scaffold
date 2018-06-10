package com.leeves.browser.web.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Description: 拦截器
 * Package: com.leeves.browser.interceptor
 *
 * @author Leeves
 * @version 1.0.0  2018-06-10
 */
//@Component
public class WebInterceptor implements HandlerInterceptor {

    /**
     * controller的方法处理之前。只能记录类名和方法
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("preHandle");
        //类名
        System.out.println("preHandle handler 方法类:" + ((HandlerMethod) handler).getBean().getClass().getName());
        //方法名
        System.out.println("preHandle handler 方法名:" + ((HandlerMethod) handler).getMethod().getName());
        request.setAttribute("interceptor_time", new Date().getTime());
        return true;
    }

    /**
     * controller的方法处理之后，但当其方法发生异常时，不调用此方法
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        Long startTime = (Long) request.getAttribute("interceptor_time");
        System.out.println("postHandle time:" + (new Date().getTime() - startTime) + "ms");
    }

    /**
     * controller的方法无论正常或抛异常都调用此方法
     * 但发生异常时，此方法Exception ex不为null。
     * ps：如果有对controler做异常处理@ExceptionHandler的话，那么这里的Exception ex为null。因为@ControllerAdvice在interceptor之前把其中某个异常处理了
     *
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion Exception name:" + ex);
        Long startTime = (Long) request.getAttribute("interceptor_time");
        System.out.println("afterCompletion time:" + (new Date().getTime() - startTime) + "ms");
    }

}
