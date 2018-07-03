package com.leeves.properties;

import lombok.Data;

/**
 * Description: TODO
 * Package: com.leeves.properties
 *
 * @author Leeves
 * @version 1.0.0  2018-07-01
 */
@Data
public class SmsCodeProperties {
    private int length =4;
    private int expireIn = 60;
    private String url;

}