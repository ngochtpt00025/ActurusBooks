package com.example.bookstore.config;

import com.example.bookstore.interceptor.AdminInterceptor;
import com.example.bookstore.interceptor.CustomerInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {
    
    private final AdminInterceptor adminInterceptor;
    private final CustomerInterceptor customerInterceptor;
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // Admin interceptor - chỉ áp dụng cho admin routes
        registry.addInterceptor(adminInterceptor)
                .addPathPatterns("/admin/**")
                .excludePathPatterns("/admin/login");
        
        // Customer interceptor - áp dụng cho customer routes
        registry.addInterceptor(customerInterceptor)
                .addPathPatterns("/profile/**", "/orders/**", "/wishlist/**", 
                               "/cart/**", "/checkout/**");
    }
}
