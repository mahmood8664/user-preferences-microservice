package com.tarabutgateway.assignment.user_pref_write.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mahmood
 * @since 9/14/21
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean(name = "swaggerSpringfoxApiDocket")
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select().paths(PathSelectors.ant("/api/**"))
                .apis(RequestHandlerSelectors.basePackage("com.tarabutgateway"))
                .build()
                .useDefaultResponseMessages(false);
    }

}
