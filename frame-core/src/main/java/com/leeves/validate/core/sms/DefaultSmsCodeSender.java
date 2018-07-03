package com.leeves.validate.core.sms;

/**
 * Description: TODO
 * Package: com.leeves.validate.core.sms
 *
 * @author Leeves
 * @version 1.0.0  2018-07-01
 */
public class DefaultSmsCodeSender implements SmsCodeSender {

    @Override
    public void send(String mobile, String code) {
        //TODO: 向手机发送短信验证码
        System.out.println("向手机:" + mobile + "  发送短信验证码：" + code);
    }

}