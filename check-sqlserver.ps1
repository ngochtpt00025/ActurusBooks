# Script kiểm tra SQL Server
Write-Host "=== Kiểm tra SQL Server ===" -ForegroundColor Green

# Kiểm tra SQL Server Service
Write-Host "1. Kiểm tra SQL Server Service..." -ForegroundColor Yellow
$sqlService = Get-Service -Name "*SQL*" -ErrorAction SilentlyContinue
if ($sqlService) {
    Write-Host "   SQL Server Services found:" -ForegroundColor Green
    $sqlService | ForEach-Object { Write-Host "   - $($_.Name): $($_.Status)" }
} else {
    Write-Host "   Không tìm thấy SQL Server Service!" -ForegroundColor Red
}

# Kiểm tra port 1433
Write-Host "`n2. Kiểm tra port 1433..." -ForegroundColor Yellow
$port1433 = netstat -an | findstr ":1433"
if ($port1433) {
    Write-Host "   Port 1433 đang được sử dụng:" -ForegroundColor Green
    Write-Host "   $port1433"
} else {
    Write-Host "   Port 1433 không được sử dụng!" -ForegroundColor Red
}

# Kiểm tra kết nối database
Write-Host "`n3. Kiểm tra kết nối database..." -ForegroundColor Yellow
try {
    $connectionString = "Server=localhost;Database=master;User Id=admin;Password=123456;TrustServerCertificate=True;"
    $connection = New-Object System.Data.SqlClient.SqlConnection($connectionString)
    $connection.Open()
    Write-Host "   Kết nối thành công!" -ForegroundColor Green
    $connection.Close()
} catch {
    Write-Host "   Lỗi kết nối: $($_.Exception.Message)" -ForegroundColor Red
}

Write-Host "`n=== Hoàn thành kiểm tra ===" -ForegroundColor Green
