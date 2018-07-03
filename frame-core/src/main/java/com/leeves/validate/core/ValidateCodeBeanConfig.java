package com.leeves.validate.core;

import com.leeves.properties.SecurityProperties;
import com.leeves.validate.core.imageCode.ImageCodeGenerator;
import com.leeves.validate.core.sms.DefaultSmsCodeSender;
import com.leeves.validate.core.sms.SmsCodeSender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Description: 配置验证码类
 * Package: com.leeves.validate.core
 *
 * @author Leeves
 * @version 1.0.0  2018-06-17
 */
@Configuration
public class ValidateCodeBeanConfig {

    @Autowired
    private SecurityProperties securityProperties;

    /**
     * 配置图形验证码
     */
    @Bean
    @ConditionalOnMissingBean(name = "imageCodeGenerator")
    public ValidateCodeGenerator imageCodeGenerator(){
        ImageCodeGenerator imageCodeGenerator = new ImageCodeGenerator();
        imageCodeGenerator.setSecurityProperties(securityProperties);
        return imageCodeGenerator;
    }

    /**
     * 配置默认短信发送验证码
     */
    @Bean
//    @ConditionalOnMissingBean(name = "smsCodeSender")
    @ConditionalOnMissingBean(SmsCodeSender.class)
    public SmsCodeSender smsCodeGenerator(){
        return new DefaultSmsCodeSender();
    }
}