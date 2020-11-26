package com.bder.manage.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "com.bder.manage.*")//  @Controller、@Service 注解；
@EnableJpaRepositories(basePackages = "com.bder.manage.*")//  @Repository 注解；
@EntityScan(basePackages = "com.bder.manage.*")//  @Entity 注解；
public class ManageWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManageWebApplication.class, args);
    }

}
