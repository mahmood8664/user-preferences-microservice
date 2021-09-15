package com.tarabutgateway.assignment.discovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author mahmood
 * @since 9/14/21
 */
@EnableEurekaServer
@SpringBootApplication
public class DiscoveryRunner {

    public static void main(String[] args) {
        SpringApplication.run(DiscoveryRunner.class, args);
    }

}
