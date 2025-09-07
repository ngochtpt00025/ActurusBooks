package com.example.bookstore.controller;

import com.example.bookstore.dto.UserDTO;
import com.example.bookstore.service.AuthService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class AuthController {
    
    @Autowired
    AuthService authService;
    
    /**
     * Trang đăng nhập
     */
    @GetMapping("/login")
    public String loginPage(@RequestParam(value = "error", required = false) String error,
                           @RequestParam(value = "logout", required = false) String logout,
                           Model model) {
        if (error != null) {
            model.addAttribute("error", "Tên đăng nhập hoặc mật khẩu không đúng");
        }
        if (logout != null) {
            model.addAttribute("message", "Bạn đã đăng xuất thành công");
        }
        return "customer/auth/login";
    }
    
    /**
     * Xử lý đăng nhập customer
     */
    @PostMapping("/login")
    public String login(@RequestParam String username,
                       @RequestParam String password,
                       HttpSession session,
                       RedirectAttributes redirectAttributes) {
        
        UserDTO user = authService.authenticate(username, password);
        
        if (user == null) {
            redirectAttributes.addAttribute("error", "true");
            return "redirect:/login";
        }
        
        // Chỉ cho phép CUSTOMER đăng nhập qua /login
        if (user.getRole() != com.example.bookstore.enums.UserRole.CUSTOMER) {
            redirectAttributes.addAttribute("error", "unauthorized");
            return "redirect:/login";
        }
        
        // Lưu thông tin user vào session
        session.setAttribute("user", user);
        session.setAttribute("userId", user.getUserId());
        session.setAttribute("userRole", user.getRole().name());
        
        return "redirect:/";
    }
    
    /**
     * Trang đăng ký
     */
    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("user", new UserDTO());
        return "customer/auth/register";
    }
    
    /**
     * Xử lý đăng ký
     */
    @PostMapping("/register")
    public String register(@RequestParam String fullName,
                          @RequestParam String email,
                          @RequestParam String password,
                          @RequestParam String phone,
                          RedirectAttributes redirectAttributes) {
        try {
            authService.register(fullName, email, password, phone);
            redirectAttributes.addFlashAttribute("message", "Đăng ký thành công! Vui lòng đăng nhập.");
            return "redirect:/login";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/register";
        }
    }
    
    /**
     * Đăng xuất customer
     */
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/?logout=true";
    }
    
    /**
     * Trang đăng nhập admin
     */
    @GetMapping("/admin/login")
    public String adminLoginPage(@RequestParam(value = "error", required = false) String error,
                                Model model) {
        if (error != null) {
            model.addAttribute("error", "Tên đăng nhập hoặc mật khẩu không đúng");
        }
        return "admin/auth/login";
    }
    
    /**
     * Xử lý đăng nhập admin
     */
    @PostMapping("/admin/login")
    public String adminLogin(@RequestParam String username,
                            @RequestParam String password,
                            HttpSession session,
                            RedirectAttributes redirectAttributes) {
        
        UserDTO user = authService.authenticate(username, password);
        
        if (user == null) {
            redirectAttributes.addAttribute("error", "true");
            return "redirect:/admin/login";
        }
        
        // Chỉ cho phép ADMIN/STAFF đăng nhập qua /admin/login
        if (user.getRole() != com.example.bookstore.enums.UserRole.ADMIN && 
            user.getRole() != com.example.bookstore.enums.UserRole.STAFF) {
            redirectAttributes.addAttribute("error", "unauthorized");
            return "redirect:/admin/login";
        }
        
        // Lưu thông tin admin vào session
        session.setAttribute("admin", user);
        session.setAttribute("adminId", user.getUserId());
        session.setAttribute("adminRole", user.getRole().name());
        
        return "redirect:/admin/dashboard";
    }
    
    /**
     * Đăng xuất admin
     */
    @GetMapping("/admin/logout")
    public String adminLogout(HttpSession session) {
        session.removeAttribute("admin");
        session.removeAttribute("adminId");
        session.removeAttribute("adminRole");
        return "redirect:/admin/login?logout=true";
    }
}
