package com.example.bookstore.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class CustomerInterceptor implements HandlerInterceptor {
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, 
                           Object handler) throws Exception {
        
        String requestURI = request.getRequestURI();
        HttpSession session = request.getSession(false);
        
        // Bỏ qua public routes
        if (isPublicRoute(requestURI)) {
            return true;
        }
        
        // Kiểm tra customer protected routes
        if (isCustomerProtectedRoute(requestURI)) {
            if (session == null || session.getAttribute("user") == null) {
                response.sendRedirect("/login");
                return false;
            }
            
            // Kiểm tra role customer
            String userRole = (String) session.getAttribute("userRole");
            if (userRole == null || !userRole.equals("CUSTOMER")) {
                response.sendRedirect("/login?error=unauthorized");
                return false;
            }
        }
        
        return true;
    }
    
    /**
     * Kiểm tra xem route có phải public không
     */
    private boolean isPublicRoute(String requestURI) {
        return requestURI.equals("/") ||
               requestURI.equals("/login") ||
               requestURI.equals("/register") ||
               requestURI.startsWith("/css/") ||
               requestURI.startsWith("/js/") ||
               requestURI.startsWith("/img/") ||
               requestURI.startsWith("/static/") ||
               requestURI.startsWith("/uploads/") ||
               requestURI.startsWith("/api/public/");
    }
    
    /**
     * Kiểm tra xem route có cần đăng nhập customer không
     */
    private boolean isCustomerProtectedRoute(String requestURI) {
        return requestURI.startsWith("/profile/") ||
               requestURI.startsWith("/orders/") ||
               requestURI.startsWith("/wishlist/") ||
               requestURI.startsWith("/cart/") ||
               requestURI.startsWith("/checkout/");
    }
}
