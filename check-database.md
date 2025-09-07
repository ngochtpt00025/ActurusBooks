# Kiểm tra Database Connection

## Các bước để sửa lỗi database

### 1. Kiểm tra SQL Server
```sql
-- Chạy script này trong SQL Server Management Studio
USE master;
GO

-- Kiểm tra xem database có tồn tại không
SELECT name FROM sys.databases WHERE name = 'ActurusBook';
GO

-- Nếu không có, tạo database
CREATE DATABASE ActurusBook;
GO
```

### 2. Kiểm tra kết nối
```sql
-- Kiểm tra user và quyền
SELECT name FROM sys.server_principals WHERE name = 'admin';
GO

-- Tạo user nếu chưa có
CREATE LOGIN admin WITH PASSWORD = '123456';
GO

-- Cấp quyền cho database
USE ActurusBook;
GO
CREATE USER admin FOR LOGIN admin;
GO
ALTER ROLE db_owner ADD MEMBER admin;
GO
```

### 3. Kiểm tra SQL Server Service
- Mở Services (services.msc)
- Tìm "SQL Server (MSSQLSERVER)" hoặc "SQL Server (SQLEXPRESS)"
- Đảm bảo service đang chạy

### 4. Kiểm tra port 1433
```cmd
netstat -an | findstr 1433
```

### 5. Test kết nối từ ứng dụng
```bash
# Chạy ứng dụng với debug
mvn spring-boot:run -Dspring-boot.run.jvmArguments="-Ddebug=true"
```

## Cấu hình đã cập nhật

### application.properties
- ✅ `spring.jpa.hibernate.ddl-auto=create-drop` - Tự động tạo bảng
- ✅ `spring.jpa.show-sql=false` - Tắt log SQL để tăng tốc
- ✅ Batch processing - Tăng tốc độ insert/update

### Database
- ✅ Tạo database `ActurusBook`
- ✅ Cấu hình user `admin` với password `123456`
- ✅ Cấp quyền `db_owner`

## Kết quả mong đợi
- ✅ Ứng dụng khởi động nhanh hơn (< 10 giây)
- ✅ Không còn lỗi database connection
- ✅ Bảng được tạo tự động từ entities
