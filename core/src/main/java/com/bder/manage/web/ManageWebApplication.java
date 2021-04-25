package com.bder.manage.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author Felix YF Dong
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.bder.manage.*")
@EnableJpaRepositories(basePackages = "com.bder.manage.*")
@EntityScan(basePackages = "com.bder.manage.*")
public class ManageWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManageWebApplication.class, args);
    }

}
