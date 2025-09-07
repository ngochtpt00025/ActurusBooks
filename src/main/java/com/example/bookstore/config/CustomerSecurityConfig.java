package com.example.bookstore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@Order(2) // Ưu tiên thấp hơn admin
public class CustomerSecurityConfig {
    
    @Bean
    public SecurityFilterChain customerFilterChain(HttpSecurity http) throws Exception {
        http
            .securityMatcher("/**") // Áp dụng cho tất cả routes không phải admin
            .authorizeHttpRequests(authz -> authz
                // Public routes - không cần đăng nhập
                .requestMatchers("/", "/login", "/register", "/css/**", "/js/**", 
                               "/img/**", "/static/**", "/uploads/**", "/api/public/**").permitAll()
                // Customer protected routes - cần đăng nhập với role CUSTOMER
                .requestMatchers("/profile/**", "/orders/**", "/wishlist/**", 
                               "/cart/**", "/checkout/**").hasRole("CUSTOMER")
                // Tất cả routes khác cho phép truy cập
                .anyRequest().permitAll()
            )
            .formLogin(form -> form
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/", true)
                .failureUrl("/login?error=true")
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/?logout=true")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll()
            )
            .sessionManagement(session -> session
                .maximumSessions(1)
                .maxSessionsPreventsLogin(false)
            )
            .csrf(csrf -> csrf.disable());
            
        return http.build();
    }
}
