package com.natwest.pravesh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@ComponentScan(basePackages = "com.natwest.pravesh")
public class PraveshApplication {

    public static void main(String[] args) {
        SpringApplication.run(PraveshApplication.class, args);
    }
}
