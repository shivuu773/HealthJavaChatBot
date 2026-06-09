@echo off
title Healthcare Chatbot - Setup and Run
color 0A

echo ================================================
echo   HEALTHCARE CHATBOT - Java Web Application
echo ================================================
echo.

REM Check for Maven
where mvn >nul 2>&1
if %errorlevel% neq 0 (
    echo [INFO] Maven not found. Downloading Maven Wrapper...
    
    REM Download mvnw using PowerShell
    powershell -Command "& {Invoke-WebRequest -Uri 'https://repo.maven.apache.org/maven2/org/apache/maven/wrapper/maven-wrapper/3.2.0/maven-wrapper-3.2.0.jar' -OutFile 'maven-wrapper.jar' -ErrorAction SilentlyContinue}"
    
    echo [INFO] Trying to use mvnw (Maven Wrapper)...
    if exist mvnw.cmd (
        call mvnw.cmd tomcat7:run
    ) else (
        echo [ERROR] Maven is required. Please install Maven from https://maven.apache.org/download.cgi
        echo [INFO] Or install via: winget install Apache.Maven
        pause
        exit /b 1
    )
) else (
    echo [INFO] Maven found. Starting application...
    echo [INFO] Building and deploying to Apache Tomcat 9 (Embedded) on port 8080...
    echo [INFO] Application will be available at: http://localhost:8080/healthcare
    echo.
    mvn tomcat7:run
)

pause
