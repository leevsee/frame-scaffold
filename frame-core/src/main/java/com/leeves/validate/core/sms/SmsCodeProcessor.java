package com.leeves.validate.core.sms;

import com.leeves.validate.core.ValidateCode;
import com.leeves.validate.core.impl.AbstractValidateCodeProcessor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.ServletRequest;

/**
 * Description: TODO
 * Package: com.leeves.validate.core.sms
 *
 * @author Leeves
 * @version 1.0.0  2018-07-01
 */
@Component
public class SmsCodeProcessor extends AbstractValidateCodeProcessor<ValidateCode> {

    @Autowired
    private SmsCodeSender smsCodeSender;

    @Override
    protected void send(ServletWebRequest request, ValidateCode validateCode) throws Exception {
        //此处应该连接短信服务商发送短信
        String mobile = ServletRequestUtils.getRequiredStringParameter((ServletRequest) request, "mobile");
        smsCodeSender.send(mobile, validateCode.getCode());
    }

}