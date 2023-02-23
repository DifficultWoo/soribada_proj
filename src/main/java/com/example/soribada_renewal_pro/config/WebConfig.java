package com.example.soribada_renewal_pro.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// CORS
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //모든 매핑경로에 대해 (registry.addMapping("/**")) or api만 허용하겠다 "/*api"
        //모든 사용자에 대해 (allowedOrigins("*"))
        // GET, POST, PUT, DELETE, PATCH, OPTION
        //모든 메서드를 허용한다 (allowedMethods("*"))
        registry.addMapping("/**").allowedOrigins("*")
        .allowedMethods("*");
    }

}