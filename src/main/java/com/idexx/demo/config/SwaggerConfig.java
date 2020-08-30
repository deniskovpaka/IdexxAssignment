package com.idexx.demo.config;

import com.idexx.demo.controller.SearchController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket searchApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(searchApiInfo())
                .enable(true)
                .useDefaultResponseMessages(false)
                .select()
                .paths(regex(SearchController.SEARCH_URI))
                .build();
    }

    private ApiInfo searchApiInfo() {
        return new ApiInfoBuilder()
                .title("Search service")
                .description("SearchService provides an API resource to fetch albums and books by search criteria.")
                .build();
    }
}
