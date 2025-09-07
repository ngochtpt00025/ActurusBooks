# Script sửa tất cả import còn lại
Write-Host "=== Sửa tất cả import còn lại ===" -ForegroundColor Green

# Tìm tất cả file Java còn có import sai
$files = Get-ChildItem -Path "book\src\main\java" -Recurse -Filter "*.java"

foreach ($file in $files) {
    $content = Get-Content $file.FullName -Raw
    if ($content -match "com\.example\.bookstore") {
        Write-Host "Sửa file: $($file.FullName)" -ForegroundColor Yellow
        
        # Thay thế tất cả com.example.bookstore thành com.acturus.book
        $content = $content -replace "com\.example\.bookstore", "com.acturus.book"
        
        # Lưu file
        Set-Content -Path $file.FullName -Value $content -NoNewline
        Write-Host "  ✓ Đã sửa" -ForegroundColor Green
    }
}

Write-Host "`n=== Hoàn thành sửa tất cả import ===" -ForegroundColor Green
