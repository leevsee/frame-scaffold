package com.leeves.browser.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Description: 启动入口
 * Package: com.leeves.demo
 *
 * @author Leeves
 * @version 1.0.0  2018-06-15
 */
@SpringBootApplication
@ComponentScan("com.leeves")
public class BrowserDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(BrowserDemoApplication.class, args);
    }

}