package com.leeves.app.demo.aspect;

import com.leeves.app.demo.dto.R;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

/**
 * Description: 校验入参错误返回
 * Package: com.leeves.app.aspect
 *
 * @author Leeves
 * @date 2018-05-09
 */
@Aspect
@Component
public class ControllerValidatorInterceptor {

    @Around("execution(* com.leeves.app.controller.*.*(..)) && args(..,bindingResult)")
    public Object doAround(ProceedingJoinPoint pjp, BindingResult bindingResult) throws Throwable {
        Object retVal;
        if (bindingResult.hasErrors()) {
            //错误返回
            retVal = R.resultError(bindingResult.getFieldError().getDefaultMessage());
        } else {
            retVal = pjp.proceed();
        }
        return retVal;
    }

}
