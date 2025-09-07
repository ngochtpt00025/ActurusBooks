package com.example.bookstore.controller;

import com.example.bookstore.dto.UserDTO;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    
    @GetMapping("/")
    public String home(HttpSession session, Model model) {
        // Kiểm tra session user
        UserDTO user = (UserDTO) session.getAttribute("user");
        UserDTO admin = (UserDTO) session.getAttribute("admin");
        
        if (admin != null) {
            model.addAttribute("admin", admin);
            model.addAttribute("isAdmin", true);
        } else if (user != null) {
            model.addAttribute("user", user);
            model.addAttribute("isLoggedIn", true);
        }
        
        return "index";
    }
}
