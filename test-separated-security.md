# Test Separated Security System

## Cấu trúc Security đã tách biệt

### 1. AdminSecurityConfig (@Order(1))
- **Scope**: `/admin/**`
- **Login**: `/admin/login`
- **Roles**: ADMIN, STAFF
- **Logout**: `/admin/logout` → `/admin/login?logout=true`

### 2. CustomerSecurityConfig (@Order(2))
- **Scope**: `/**` (tất cả routes không phải admin)
- **Login**: `/login`
- **Roles**: CUSTOMER
- **Logout**: `/logout` → `/?logout=true`

### 3. Interceptors
- **AdminInterceptor**: Kiểm tra admin routes
- **CustomerInterceptor**: Kiểm tra customer routes

## Test Cases

### Test 1: Admin Login Flow
1. Truy cập: `http://localhost:8080/admin/dashboard`
2. Kết quả: Redirect đến `/admin/login`
3. Đăng nhập với admin account
4. Kết quả: Redirect đến `/admin/dashboard`

### Test 2: Customer Login Flow
1. Truy cập: `http://localhost:8080/profile`
2. Kết quả: Redirect đến `/login`
3. Đăng nhập với customer account
4. Kết quả: Redirect đến `/`

### Test 3: Role Validation
1. Admin đăng nhập qua `/login` → Error: unauthorized
2. Customer đăng nhập qua `/admin/login` → Error: unauthorized
3. Admin truy cập `/profile` → Redirect đến `/login`
4. Customer truy cập `/admin/dashboard` → Redirect đến `/admin/login`

### Test 4: Session Management
1. Admin logout → Redirect đến `/admin/login?logout=true`
2. Customer logout → Redirect đến `/?logout=true`
3. Session được clear hoàn toàn

## Cấu trúc Files

### Security Configs
- `AdminSecurityConfig.java` - Cấu hình security cho admin
- `CustomerSecurityConfig.java` - Cấu hình security cho customer

### Interceptors
- `AdminInterceptor.java` - Kiểm tra admin routes
- `CustomerInterceptor.java` - Kiểm tra customer routes

### Controllers
- `AuthController.java` - Xử lý login/logout cho cả admin và customer

### Templates
- `admin/auth/login.html` - Trang đăng nhập admin
- `customer/auth/login.html` - Trang đăng nhập customer

## Ưu điểm của cấu trúc mới

1. **Tách biệt rõ ràng**: Admin và Customer có security riêng biệt
2. **Dễ bảo trì**: Mỗi role có config riêng
3. **Linh hoạt**: Có thể cấu hình khác nhau cho từng role
4. **An toàn**: Role validation chặt chẽ
5. **UX tốt**: Redirect logic phù hợp với từng role
