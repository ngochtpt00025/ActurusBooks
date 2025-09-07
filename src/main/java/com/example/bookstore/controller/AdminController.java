package com.example.bookstore.controller;

import com.example.bookstore.dto.UserDTO;
import com.example.bookstore.service.AuthService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
@SuppressWarnings("unused")
public class AdminController {
    
    private final AuthService authService;
    
    /**
     * Dashboard admin
     */
    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        // Kiểm tra đăng nhập admin
        UserDTO admin = (UserDTO) session.getAttribute("admin");
        if (admin == null) {
            return "redirect:/admin/login";
        }
        
        model.addAttribute("admin", admin);
        model.addAttribute("pageTitle", "Dashboard");
        
        // TODO: Thêm thống kê dashboard
        // - Tổng số đơn hàng
        // - Doanh thu hôm nay
        // - Số lượng sản phẩm
        // - Số lượng khách hàng
        
        return "admin/dashboard";
    }
    
    /**
     * Quản lý người dùng
     */
    @GetMapping("/users")
    public String users(HttpSession session, Model model) {
        UserDTO admin = (UserDTO) session.getAttribute("admin");
        if (admin == null) {
            return "redirect:/admin/login";
        }
        
        model.addAttribute("admin", admin);
        model.addAttribute("pageTitle", "Quản lý người dùng");
        
        return "admin/user/users";
    }
    
    /**
     * Quản lý sản phẩm
     */
    @GetMapping("/products")
    public String products(HttpSession session, Model model) {
        UserDTO admin = (UserDTO) session.getAttribute("admin");
        if (admin == null) {
            return "redirect:/admin/login";
        }
        
        model.addAttribute("admin", admin);
        model.addAttribute("pageTitle", "Quản lý sản phẩm");
        
        return "admin/product/products";
    }
    
    /**
     * Quản lý đơn hàng
     */
    @GetMapping("/orders")
    public String orders(HttpSession session, Model model) {
        UserDTO admin = (UserDTO) session.getAttribute("admin");
        if (admin == null) {
            return "redirect:/admin/login";
        }
        
        model.addAttribute("admin", admin);
        model.addAttribute("pageTitle", "Quản lý đơn hàng");
        
        return "admin/order/orders";
    }
    
    /**
     * Quản lý khuyến mãi
     */
    @GetMapping("/promotions")
    public String promotions(HttpSession session, Model model) {
        UserDTO admin = (UserDTO) session.getAttribute("admin");
        if (admin == null) {
            return "redirect:/admin/login";
        }
        
        model.addAttribute("admin", admin);
        model.addAttribute("pageTitle", "Quản lý khuyến mãi");
        
        return "admin/promotion/promotions";
    }
    
    /**
     * Quản lý danh mục
     */
    @GetMapping("/categories")
    public String categories(HttpSession session, Model model) {
        UserDTO admin = (UserDTO) session.getAttribute("admin");
        if (admin == null) {
            return "redirect:/admin/login";
        }
        
        model.addAttribute("admin", admin);
        model.addAttribute("pageTitle", "Quản lý danh mục");
        
        return "admin/category/categories";
    }
    
    /**
     * Quản lý tác giả
     */
    @GetMapping("/authors")
    public String authors(HttpSession session, Model model) {
        UserDTO admin = (UserDTO) session.getAttribute("admin");
        if (admin == null) {
            return "redirect:/admin/login";
        }
        
        model.addAttribute("admin", admin);
        model.addAttribute("pageTitle", "Quản lý tác giả");
        
        return "admin/author/authors";
    }
    
    /**
     * Quản lý nhà xuất bản
     */
    @GetMapping("/publishers")
    public String publishers(HttpSession session, Model model) {
        UserDTO admin = (UserDTO) session.getAttribute("admin");
        if (admin == null) {
            return "redirect:/admin/login";
        }
        
        model.addAttribute("admin", admin);
        model.addAttribute("pageTitle", "Quản lý nhà xuất bản");
        
        return "admin/publisher/publishers";
    }
    
    /**
     * Quản lý đánh giá
     */
    @GetMapping("/reviews")
    public String reviews(HttpSession session, Model model) {
        UserDTO admin = (UserDTO) session.getAttribute("admin");
        if (admin == null) {
            return "redirect:/admin/login";
        }
        
        model.addAttribute("admin", admin);
        model.addAttribute("pageTitle", "Quản lý đánh giá");
        
        return "admin/review/reviews";
    }
    
    /**
     * Báo cáo thống kê
     */
    @GetMapping("/reports")
    public String reports(HttpSession session, Model model) {
        UserDTO admin = (UserDTO) session.getAttribute("admin");
        if (admin == null) {
            return "redirect:/admin/login";
        }
        
        model.addAttribute("admin", admin);
        model.addAttribute("pageTitle", "Báo cáo thống kê");
        
        return "admin/report/reports";
    }
}
