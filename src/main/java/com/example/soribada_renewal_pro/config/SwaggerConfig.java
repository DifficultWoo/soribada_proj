package com.example.soribada_renewal_pro.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI floOpenAPI() {
        Info info = 
        new Info().version("0.0.1").title("SORI서비스 API").description("SORI서비스 Restful API 명세서");
        // Info info = new Info().version("내가정함").title("내가정함 API").description("내가정함 Restful API 명세서");
        return new OpenAPI().info(info);
    }
}