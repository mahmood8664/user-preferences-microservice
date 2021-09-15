package com.tarabutgateway.assignment.gateway.config;

import org.springframework.cloud.gateway.config.GatewayProperties;
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

    private final GatewayProperties gatewayProperties;

    public SwaggerConfig(GatewayProperties gatewayProperties) {
        this.gatewayProperties = gatewayProperties;
    }

    @Bean(name = "swaggerSpringfoxApiDocket")
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select().paths(PathSelectors.ant("/api/**"))
                .apis(RequestHandlerSelectors.basePackage("com.tarabutgateway"))
                .build()
                .useDefaultResponseMessages(false);
    }

    @Primary
    @Bean
    public SwaggerResourcesProvider swaggerResourcesProvider() {
        return () -> {
            List<SwaggerResource> resources = new ArrayList<>();
            gatewayProperties.getRoutes()
                    .forEach(route -> resources.add(createResource(route.getId(), route.getId(), "2.0")));
            return resources;
        };
    }

    private SwaggerResource createResource(String name, String location, @SuppressWarnings("SameParameterValue") String version) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation("/" + location + "/v2/api-docs");
        swaggerResource.setSwaggerVersion(version);
        return swaggerResource;
    }

}
