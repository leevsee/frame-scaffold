package com.leeves.properties;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Description: \
 * Package: com.leeves.properties
 *
 * @author Leeves
 * @version 1.0.0  2018-06-16
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ImageCodeProperties extends SmsCodeProperties {

    public ImageCodeProperties() {
        setLength(4);
    }

    private int width = 67;
    private int height = 23;

}