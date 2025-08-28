package com.myproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // 关闭 csrf（开发环境建议关闭，生产要看情况）
                .csrf(csrf -> csrf.disable())

                // 配置请求权限
                .authorizeHttpRequests(auth -> auth
                        // 放行测试接口和 swagger
                        .requestMatchers("/success", "/throw-error", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
                        // 其他接口需要认证
                        .anyRequest().authenticated()
                )

                // 禁用默认登录页（因为我们会用 JWT，不用表单登录）
                .formLogin(form -> form.disable());

        return http.build();
    }
}
