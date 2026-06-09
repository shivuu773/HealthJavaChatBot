<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/views/includes/header.jsp"/>

<section class="page-hero">
    <h1><i class="fas fa-virus"></i> Disease Information Library</h1>
    <p>Comprehensive medical information for 20+ common diseases</p>
</section>

<section class="disease-list-section">
    <div class="search-bar-container">
        <form action="${pageContext.request.contextPath}/search" method="get" class="inline-search">
            <input type="text" name="q" placeholder="Search diseases or symptoms..." class="search-input">
            <button type="submit" class="search-btn"><i class="fas fa-search"></i> Search</button>
        </form>
    </div>

    <div class="disease-grid">
        <c:forEach var="d" items="${diseases}">
        <div class="disease-card">
            <div class="disease-card-header">
                <div class="disease-card-icon">
                    <c:choose>
                        <c:when test="${d.name.contains('Cold')}"><i class="fas fa-snowflake"></i></c:when>
                        <c:when test="${d.name.contains('Flu') || d.name.contains('Influenza')}"><i class="fas fa-thermometer-half"></i></c:when>
                        <c:when test="${d.name.contains('COVID')}"><i class="fas fa-virus"></i></c:when>
                        <c:when test="${d.name.contains('Diabetes')}"><i class="fas fa-tint"></i></c:when>
                        <c:when test="${d.name.contains('Hypertension')}"><i class="fas fa-heart-pulse"></i></c:when>
                        <c:when test="${d.name.contains('Asthma')}"><i class="fas fa-lungs"></i></c:when>
                        <c:when test="${d.name.contains('Migraine')}"><i class="fas fa-brain"></i></c:when>
                        <c:when test="${d.name.contains('Dengue')}"><i class="fas fa-bug"></i></c:when>
                        <c:when test="${d.name.contains('Malaria')}"><i class="fas fa-mosquito"></i></c:when>
                        <c:when test="${d.name.contains('Depression')}"><i class="fas fa-cloud-rain"></i></c:when>
                        <c:when test="${d.name.contains('Anxiety')}"><i class="fas fa-wind"></i></c:when>
                        <c:when test="${d.name.contains('Obesity')}"><i class="fas fa-weight-scale"></i></c:when>
                        <c:when test="${d.name.contains('Arthritis')}"><i class="fas fa-bone"></i></c:when>
                        <c:when test="${d.name.contains('Anemia')}"><i class="fas fa-droplet"></i></c:when>
                        <c:otherwise><i class="fas fa-stethoscope"></i></c:otherwise>
                    </c:choose>
                </div>
                <h3 class="disease-card-title">${d.name}</h3>
            </div>
            <p class="disease-desc">${d.description}</p>
            <div class="disease-symptoms-preview">
                <strong><i class="fas fa-circle-dot"></i> Key Symptoms:</strong>
                <c:set var="syms" value="${d.symptomsArray}"/>
                <c:forEach var="sym" items="${syms}" begin="0" end="2">
                    <span class="symptom-tag">${sym}</span>
                </c:forEach>
            </div>
            <a href="${pageContext.request.contextPath}/disease?action=view&id=${d.id}" class="view-btn">
                View Full Details <i class="fas fa-arrow-right"></i>
            </a>
        </div>
        </c:forEach>
    </div>
</section>

<jsp:include page="/WEB-INF/views/includes/footer.jsp"/>
