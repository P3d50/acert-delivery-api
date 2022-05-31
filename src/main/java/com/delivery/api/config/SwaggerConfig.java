package com.delivery.api.config;

import io.swagger.annotations.Api;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

import static springfox.documentation.builders.PathSelectors.any;

@Configuration
@EnableSwagger2
@Api(value = "MerchantControllerAPI", produces = MediaType.APPLICATION_JSON_VALUE)
public class SwaggerConfig {

    @Bean
    public Docket deliveryAPI() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.delivery.api.controller"))
                .paths(any())
                .build()
                .apiInfo(metaInfo());
    }

    private ApiInfo metaInfo() {
        return new ApiInfo(
                "Delivery API REST",
                "REST API for delivery service",
                "1.0",
                "Terms of Service",
                new Contact(
                        "Pedro Ramos",
                        "https://www.linkedin.com/in/pedro-ramos-3b35aaa0/",
                        "rpedromarcos@gmail.com"),
                "MIT License",
                "https://www.mit.edu/~amini/LICENSE.md",
                new ArrayList<VendorExtension>());
    }

}