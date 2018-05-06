package com.leeves.fastjson.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Description: TODO
 * Package: com.leeves.fastjsondemo.annotation
 *
 * @author Leeves
 * @date 2018-05-01
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MoreSerializeField {
    SerializeField[] value() default {};
}
