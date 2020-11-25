package com.bder.manage.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={"com.bder.manage.*"})
public class ManageWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManageWebApplication.class, args);
    }

}
