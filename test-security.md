# Test Security System

## Lỗi đã sửa
- ✅ Sửa lỗi `@PrePersist` trùng lặp trong entity `Review`
- ✅ Tách biệt `@PrePersist` và `@PreUpdate` methods

## Cách test hệ thống

### 1. Chạy ứng dụng
```bash
mvn spring-boot:run
```

### 2. Test Admin Login
1. Truy cập: http://localhost:8080/admin/dashboard
2. Kết quả: Redirect đến /admin/login
3. Đăng nhập với admin account
4. Kết quả: Redirect đến /admin/dashboard

### 3. Test Customer Login
1. Truy cập: http://localhost:8080/profile
2. Kết quả: Redirect đến /login
3. Đăng nhập với customer account
4. Kết quả: Redirect đến /

### 4. Test Security
- Admin routes chỉ cho ADMIN/STAFF
- Customer routes cần đăng nhập
- Public routes cho tất cả

## Entities đã kiểm tra
- ✅ Review - Fixed @PrePersist issue
- ✅ Orders - OK
- ✅ User - OK
- ✅ Cart - OK
- ✅ Edition - OK
- ✅ All other entities - OK
