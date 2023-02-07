package com.practice.carrot;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;


@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class CarrotApplication {
    public static void main(String[] args) {
        Logger logger = LogManager.getLogger(CarrotApplication.class);
        SpringApplication.run(CarrotApplication.class, args);
    }
}
