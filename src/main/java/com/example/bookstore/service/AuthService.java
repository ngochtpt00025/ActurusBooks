package com.example.bookstore.service;

import com.example.bookstore.dto.UserDTO;
import com.example.bookstore.entity.User;
import com.example.bookstore.enums.UserRole;
import com.example.bookstore.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    
    private final UserRepository userRepository;
    
    /**
     * Mã hóa mật khẩu đơn giản (Base64 + reverse)
     * Chỉ để demo, không an toàn cho production
     */
    private String simpleEncode(String password) {
        if (password == null) return null;
        // Đảo ngược chuỗi và encode base64
        return java.util.Base64.getEncoder().encodeToString(
            new StringBuilder(password).reverse().toString().getBytes()
        );
    }
    
    /**
     * Giải mã mật khẩu đơn giản
     */
    private String simpleDecode(String encodedPassword) {
        if (encodedPassword == null) return null;
        try {
            // Decode base64 và đảo ngược lại
            String decoded = new String(java.util.Base64.getDecoder().decode(encodedPassword));
            return new StringBuilder(decoded).reverse().toString();
        } catch (Exception e) {
            return encodedPassword; // Trả về nguyên gốc nếu lỗi
        }
    }
    
    /**
     * Xác thực người dùng
     */
    public UserDTO authenticate(String email, String password) {
        Optional<User> userOpt = userRepository.findByEmail(email);
        
        if (userOpt.isEmpty()) {
            return null;
        }
        
        User user = userOpt.get();
        
        if (!password.equals(user.getPassword())) {
            return null;
        }
        
        // Kiểm tra tài khoản có active không
        if (!Boolean.TRUE.equals(user.getIsActive())) {
            return null;
        }
        
        return convertToDTO(user);
    }
    
    /**
     * Xác thực admin
     */
    public UserDTO authenticateAdmin(String email, String password) {
        UserDTO user = authenticate(email, password);
        
        if (user == null) {
            return null;
        }
        
        // Kiểm tra quyền admin hoặc staff
        if (user.getRole() != UserRole.ADMIN && user.getRole() != UserRole.STAFF) {
            return null;
        }
        
        return user;
    }
    
    /**
     * Đăng ký người dùng mới
     */
    public UserDTO register(String fullName, String email, String password, String phone) {
        // Kiểm tra email đã tồn tại chưa
        if (userRepository.findByEmail(email).isPresent()) {
            throw new RuntimeException("Email đã tồn tại");
        }
        
        User user = User.builder()
                .fullName(fullName)
                .email(email)
                .password(simpleEncode(password)) // Mã hóa đơn giản
                .phone(phone)
                .role(UserRole.CUSTOMER)
                .isActive(true)
                .build();
        
        User savedUser = userRepository.save(user);
        return convertToDTO(savedUser);
    }
    
    /**
     * Lấy thông tin người dùng theo email
     */
    public UserDTO getUserByEmail(String email) {
        Optional<User> userOpt = userRepository.findByEmail(email);
        return userOpt.map(this::convertToDTO).orElse(null);
    }
    
    /**
     * Cập nhật thông tin người dùng
     */
    public UserDTO updateUser(UserDTO userDTO) {
        Optional<User> userOpt = userRepository.findById(userDTO.getUserId());
        
        if (userOpt.isEmpty()) {
            throw new RuntimeException("Không tìm thấy người dùng");
        }
        
        User user = userOpt.get();
        user.setFullName(userDTO.getFullName());
        user.setPhone(userDTO.getPhone());
        user.setGender(userDTO.getGender());
        user.setBirthday(userDTO.getBirthday());
        
        User savedUser = userRepository.save(user);
        return convertToDTO(savedUser);
    }
    
    /**
     * Đổi mật khẩu
     */
    public boolean changePassword(Integer userId, String oldPassword, String newPassword) {
        Optional<User> userOpt = userRepository.findById(userId);
        
        if (userOpt.isEmpty()) {
            return false;
        }
        
        User user = userOpt.get();
        
        // Kiểm tra mật khẩu cũ
        String storedPassword = simpleDecode(user.getPassword());
        if (!oldPassword.equals(storedPassword)) {
            return false;
        }
        
        // Cập nhật mật khẩu mới (mã hóa đơn giản)
        user.setPassword(simpleEncode(newPassword));
        userRepository.save(user);
        
        return true;
    }
    
    /**
     * Chuyển đổi Entity sang DTO
     */
    private UserDTO convertToDTO(User user) {
        return UserDTO.builder()
                .userId(user.getUserId())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .gender(user.getGender())
                .birthday(user.getBirthday())
                .hireDate(user.getHireDate())
                .citizenId(user.getCitizenId())
                .role(user.getRole())
                .isActive(user.getIsActive())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }
}
