package com.leeves.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Description: TODO
 * Package: com.leeves.app
 *
 * @author Leeves
 * @date 2018-05-06
 */
@SpringBootApplication
@ComponentScan("com.leeves")
public class FrameAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(FrameAppApplication.class, args);
    }

}
