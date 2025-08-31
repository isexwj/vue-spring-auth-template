package com.myproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")                     // 所有接口
                        .allowedOrigins("http://localhost:5173") // 允许前端访问的域名
                        .allowedMethods("*")                     // GET, POST, PUT, DELETE 等
                        .allowCredentials(true)                 // 允许携带 Cookie
                        .allowedHeaders("*");                   // 所有请求头
            }
        };
    }
}

