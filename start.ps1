$mavenUrl = 'https://repo.maven.apache.org/maven2/org/apache/maven/apache-maven/3.9.6/apache-maven-3.9.6-bin.zip'
$zipPath = "$env:USERPROFILE\maven.zip"
$mvnDir = "$env:USERPROFILE\maven"
$mvnBin = "$mvnDir\apache-maven-3.9.6\bin\mvn.cmd"

if (!(Test-Path $mvnBin)) {
    Write-Host "[1/3] Downloading Apache Maven 3.9.6..." -ForegroundColor Cyan
    Invoke-WebRequest -Uri $mavenUrl -OutFile $zipPath
    Write-Host "[2/3] Extracting Maven..." -ForegroundColor Cyan
    Expand-Archive -Path $zipPath -DestinationPath $mvnDir -Force
    Remove-Item $zipPath -ErrorAction SilentlyContinue
    Write-Host "[3/3] Maven ready!" -ForegroundColor Green
} else {
    Write-Host "[OK] Maven already downloaded." -ForegroundColor Green
}

Write-Host ""
Write-Host "Starting Healthcare Chatbot on http://localhost:8080/healthcare ..." -ForegroundColor Yellow
Write-Host "Running on Apache Tomcat 9 (Embedded via Maven)" -ForegroundColor Cyan
Write-Host "Press Ctrl+C to stop." -ForegroundColor Gray
Write-Host ""

Set-Location $PSScriptRoot
& $mvnBin tomcat7:run
