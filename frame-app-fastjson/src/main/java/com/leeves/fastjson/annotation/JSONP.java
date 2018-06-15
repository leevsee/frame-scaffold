package com.leeves.fastjson.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Description: Jsonp注解
 * Package: com.leeves.fastjson.annotation
 *
 * @author Leeves
 * @version 1.0.0  2018-06-15
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface JSONP {

    String value() default "callback";
}