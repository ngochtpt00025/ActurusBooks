package com.example.bookstore.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AdminInterceptor implements HandlerInterceptor {
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, 
                           Object handler) throws Exception {
        
        String requestURI = request.getRequestURI();
        HttpSession session = request.getSession(false);
        
        // Bỏ qua admin login page
        if (requestURI.equals("/admin/login")) {
            return true;
        }
        
        // Kiểm tra session admin
        if (session == null || session.getAttribute("admin") == null) {
            response.sendRedirect("/admin/login");
            return false;
        }
        
        // Kiểm tra quyền admin
        String adminRole = (String) session.getAttribute("adminRole");
        if (adminRole == null || (!adminRole.equals("ADMIN") && !adminRole.equals("STAFF"))) {
            response.sendRedirect("/admin/login?error=unauthorized");
            return false;
        }
        
        // Kiểm tra quyền chi tiết cho các chức năng admin
        if (requestURI.startsWith("/admin/users") && !adminRole.equals("ADMIN")) {
            response.sendRedirect("/admin/dashboard?error=insufficient_permission");
            return false;
        }
        
        return true;
    }
}
