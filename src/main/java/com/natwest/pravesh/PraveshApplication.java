package com.natwest.pravesh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@ComponentScan(basePackages = "com.natwest.pravesh")
@EnableScheduling
public class PraveshApplication {

    public static void main(String[] args) {
        SpringApplication.run(PraveshApplication.class, args);
    }
}
