<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>HealthCare Chatbot - AI-Powered Medical Assistant</title>
    <meta name="description" content="AI-powered healthcare chatbot for disease information, symptom checking, medicine guidance, and appointment booking.">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;500;600;700;800&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
</head>
<body>
<nav class="navbar">
    <div class="nav-brand">
        <i class="fas fa-heartbeat pulse-icon"></i>
        <span>HealthCare AI</span>
    </div>
    <div class="nav-links" id="navLinks">
        <a href="${pageContext.request.contextPath}/home" class="nav-link"><i class="fas fa-home"></i> Home</a>
        <a href="${pageContext.request.contextPath}/disease" class="nav-link"><i class="fas fa-virus"></i> Diseases</a>
        <a href="${pageContext.request.contextPath}/search" class="nav-link"><i class="fas fa-search"></i> Search</a>
        <a href="${pageContext.request.contextPath}/appointment" class="nav-link"><i class="fas fa-calendar-plus"></i> Appointment</a>
        <a href="${pageContext.request.contextPath}/home#chatbot" class="nav-link nav-cta"><i class="fas fa-robot"></i> Chat Now</a>
    </div>
    <button class="hamburger" id="hamburger" onclick="toggleNav()">
        <i class="fas fa-bars"></i>
    </button>
</nav>
<script>
function toggleNav() {
    document.getElementById('navLinks').classList.toggle('active');
}
</script>
