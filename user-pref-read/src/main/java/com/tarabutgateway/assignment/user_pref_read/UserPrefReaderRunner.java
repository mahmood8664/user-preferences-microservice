package com.tarabutgateway.assignment.user_pref_read;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author mahmood
 * @since 9/14/21
 */
@SpringBootApplication
@EnableEurekaClient
public class UserPrefReaderRunner {

    public static void main(String[] args) {
        SpringApplication.run(UserPrefReaderRunner.class, args);
    }

}
