package com.github.oahnus.travisdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * Created by oahnus on 2018/11/16
 * 15:57.
 */
@SpringBootApplication
@EnableAutoConfiguration(exclude = DataSourceAutoConfiguration.class)
public class TravisDemoSpringBootApplication {
    public static void main(String[] args) {
        SpringApplication.run(TravisDemoSpringBootApplication.class, args);
    }

}
