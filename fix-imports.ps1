# Script sửa lỗi import package
Write-Host "=== Sửa lỗi import package ===" -ForegroundColor Green

# Thay thế tất cả com.example.bookstore thành com.acturus.book
$files = Get-ChildItem -Path "src\main\java" -Recurse -Filter "*.java"

foreach ($file in $files) {
    $content = Get-Content $file.FullName -Raw
    if ($content -match "com\.example\.bookstore") {
        Write-Host "Sửa file: $($file.FullName)" -ForegroundColor Yellow
        
        # Thay thế package declaration
        $content = $content -replace "package com\.example\.bookstore", "package com.acturus.book"
        
        # Thay thế import statements
        $content = $content -replace "import com\.example\.bookstore", "import com.acturus.book"
        
        # Thay thế fully qualified names
        $content = $content -replace "com\.example\.bookstore\.", "com.acturus.book."
        
        # Lưu file
        Set-Content -Path $file.FullName -Value $content -NoNewline
        Write-Host "  ✓ Đã sửa" -ForegroundColor Green
    }
}

Write-Host "`n=== Hoàn thành sửa lỗi import ===" -ForegroundColor Green
