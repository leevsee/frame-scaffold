package com.leeves.validate.core.sms;

/**
 * Description: TODO
 * Package: com.leeves.validate.core.sms
 *
 * @author Leeves
 * @version 1.0.0  2018-07-01
 */
public interface SmsCodeSender {
    void send(String mobile, String code);
}