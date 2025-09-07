# Script sửa tất cả import trong dự án book
Write-Host "=== Sửa tất cả import package ===" -ForegroundColor Green

# Tìm tất cả file Java
$files = Get-ChildItem -Path "book\src\main\java" -Recurse -Filter "*.java"

$count = 0
foreach ($file in $files) {
    $content = Get-Content $file.FullName -Raw
    if ($content -match "com\.example\.bookstore") {
        $count++
        Write-Host "Sửa file $count : $($file.Name)" -ForegroundColor Yellow
        
        # Thay thế tất cả com.example.bookstore thành com.acturus.book
        $content = $content -replace "com\.example\.bookstore", "com.acturus.book"
        
        # Lưu file
        Set-Content -Path $file.FullName -Value $content -NoNewline
    }
}

Write-Host "`n=== Đã sửa $count files ===" -ForegroundColor Green
Write-Host "Hoàn thành sửa lỗi import package!" -ForegroundColor Green
