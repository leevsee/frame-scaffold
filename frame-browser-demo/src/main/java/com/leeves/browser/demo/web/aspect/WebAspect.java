package com.leeves.browser.demo.web.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;

/**
 * Description:
 * Package: com.leeves.demo.web.aspect
 *
 * @author Leeves
 * @version 1.0.0  2018-06-15
 */
//@Aspect
//@Component
public class WebAspect {

    /**
     * 任何的返回值：*
     * 执行：execution
     * 全类名：com.leeves.TestController
     * 里面所有的方法：.*
     * 里面任意的参数：(..)
     */
    @Around("execution(* com.leeves.browser.demo.controller.TestController.*(..))")
    public Object handlerControllerMethod(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println(Arrays.toString(pjp.getArgs()));
        Long startTime = new Date().getTime();
        Object proceed = pjp.proceed();
        System.out.println("aspect time:" + (new Date().getTime() - startTime) + "ms");
        return proceed;
    }

}