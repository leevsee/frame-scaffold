package com.leeves.app.demo.annotation;


import org.apache.commons.lang.StringUtils;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.regex.Pattern;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;

import lombok.extern.slf4j.Slf4j;


/**
 * Description: 电话号码验证注解
 * Package: com.leeves.app.annotation
 *
 * @author Leeves
 * @date 2018-05-08
 */
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CheckPhone.ValidPhoneChecker.class)
@Documented
public @interface CheckPhone {

    String message() default "this num is not a phone num";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    /**
     * 注解实现
     */
    @Slf4j
    class ValidPhoneChecker implements ConstraintValidator<CheckPhone, String> {

        /**
         * 初始化方法
         */
        @Override
        public void initialize(CheckPhone constraintAnnotation) {

        }

        /**
         * 验证方法
         */
        @Override
        public boolean isValid(String value, ConstraintValidatorContext context) {
            if (StringUtils.isBlank(value)){
                return true;
            }
            String pattern = "^1[34578]\\d{9}$";
            return Pattern.matches(pattern, value);
        }
    }

}
