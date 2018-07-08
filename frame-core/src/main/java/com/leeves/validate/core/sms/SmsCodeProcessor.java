package com.leeves.validate.core.sms;

import com.leeves.properties.SecurityConstants;
import com.leeves.validate.core.ValidateCode;
import com.leeves.validate.core.impl.AbstractValidateCodeProcessor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.ServletRequest;

/**
 * Description: 短信验证码处理器
 * Package: com.leeves.validate.core.sms
 *
 * @author Leeves
 * @version 1.0.0  2018-07-01
 */
@Component("smsValidateCodeProcessor")
public class SmsCodeProcessor extends AbstractValidateCodeProcessor<ValidateCode> {

    @Autowired
    private SmsCodeSender smsCodeSender;

    @Override
    protected void send(ServletWebRequest request, ValidateCode validateCode) throws Exception {
        //此处应该连接短信服务商发送短信
        String mobile = ServletRequestUtils.getRequiredStringParameter(request.getRequest(), SecurityConstants.DEFAULT_PARAMETER_NAME_MOBILE);
        smsCodeSender.send(mobile, validateCode.getCode());
    }

}