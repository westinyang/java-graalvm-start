package com.mueeee.webspringboot;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Application
 */
@SpringBootApplication
public class WebSpringBootApp {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(WebSpringBootApp.class);
        springApplication.setBannerMode(Banner.Mode.CONSOLE);   // Banner.Mode.OFF : Close Banner
        springApplication.run(args);
    }

}
