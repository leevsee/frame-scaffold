package com.leeves.app.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Description: App启动类
 * Package: com.leeves.demo
 *
 * @author Leeves
 * @version 1.0.0  2018-06-15
 */
@SpringBootApplication
@ComponentScan("com.leeves")
public class AppDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppDemoApplication.class, args);
    }

}