package com.leeves.properties;

import lombok.Data;

/**
 * Description:
 * Package: com.leeves.properties
 *
 * @author Leeves
 * @version 1.0.0  2018-06-16
 */
@Data
public class ValidateCodeProperites {

    private ImageCodeProperties image = new ImageCodeProperties();
    private SmsCodeProperties sms = new SmsCodeProperties();

}