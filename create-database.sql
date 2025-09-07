-- Tạo database ActurusBook
USE master;
GO

-- Kiểm tra xem database có tồn tại không
IF NOT EXISTS (SELECT name FROM sys.databases WHERE name = 'ActurusBook')
BEGIN
    CREATE DATABASE ActurusBook;
    PRINT 'Database ActurusBook đã được tạo thành công';
END
ELSE
BEGIN
    PRINT 'Database ActurusBook đã tồn tại';
END
GO

-- Sử dụng database ActurusBook
USE ActurusBook;
GO

-- Kiểm tra kết nối
SELECT 'Database ActurusBook đã sẵn sàng sử dụng' AS Status;
GO
