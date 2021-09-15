package com.tarabutgateway.assignment.user_pref_write.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * @author mahmood
 * @since 9/14/21
 */
@Configuration
@EnableMongoRepositories(basePackages = "com.tarabutgateway.assignment.user_pref_write.repository")
public class MongoConfig {
}
