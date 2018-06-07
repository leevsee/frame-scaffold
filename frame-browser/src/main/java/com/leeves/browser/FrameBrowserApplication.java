package com.leeves.browser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Description: TODO
 * Package: com.leeves.browser
 *
 * @author Leeves
 * @date 2018-05-14
 */
@SpringBootApplication
@ComponentScan("com.leeves")
public class FrameBrowserApplication {

    public static void main(String[] args) {
        SpringApplication.run(FrameBrowserApplication.class, args);
    }

}

