package com.leeves;

import com.leeves.properties.SecurityProperties;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Description:
 * Package: PACKAGE_NAME
 *
 * @author Leeves
 * @version 1.0.0  2018-06-15
 */
@Configuration
@EnableConfigurationProperties({SecurityProperties.class})
public class SecurityCoreConfig {

}