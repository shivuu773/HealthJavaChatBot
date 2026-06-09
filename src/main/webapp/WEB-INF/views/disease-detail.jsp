<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/views/includes/header.jsp"/>

<c:if test="${empty disease}">
    <jsp:forward page="/disease"/>
</c:if>

<section class="page-hero">
    <h1><i class="fas fa-stethoscope"></i> ${disease.name}</h1>
    <p>${disease.description}</p>
    <div class="breadcrumb">
        <a href="${pageContext.request.contextPath}/">Home</a> &rsaquo;
        <a href="${pageContext.request.contextPath}/disease">Diseases</a> &rsaquo;
        <span>${disease.name}</span>
    </div>
</section>

<section class="disease-detail-section">
    <div class="detail-grid">

        <div class="detail-card symptoms-card">
            <div class="detail-card-header"><i class="fas fa-thermometer-half"></i> Symptoms</div>
            <ul class="detail-list">
                <c:forEach var="s" items="${disease.symptomsArray}">
                    <li><i class="fas fa-circle-dot"></i> ${s.trim()}</li>
                </c:forEach>
            </ul>
        </div>

        <div class="detail-card causes-card">
            <div class="detail-card-header"><i class="fas fa-microscope"></i> Causes</div>
            <ul class="detail-list">
                <c:forEach var="c" items="${disease.causesArray}">
                    <li><i class="fas fa-chevron-right"></i> ${c.trim()}</li>
                </c:forEach>
            </ul>
        </div>

        <div class="detail-card medicine-card">
            <div class="detail-card-header"><i class="fas fa-pills"></i> Recommended Medicines</div>
            <div class="disclaimer-badge"><i class="fas fa-info-circle"></i> OTC guidance only. Prescription meds require doctor approval.</div>
            <ul class="detail-list">
                <c:forEach var="m" items="${disease.medicinesArray}">
                    <li><i class="fas fa-capsules"></i> ${m.trim()}</li>
                </c:forEach>
            </ul>
        </div>

        <div class="detail-card precaution-card">
            <div class="detail-card-header"><i class="fas fa-shield-alt"></i> Precautions</div>
            <ul class="detail-list">
                <c:forEach var="p" items="${disease.precautionsArray}">
                    <li><i class="fas fa-check-circle"></i> ${p.trim()}</li>
                </c:forEach>
            </ul>
        </div>

        <div class="detail-card prevention-card">
            <div class="detail-card-header"><i class="fas fa-leaf"></i> Prevention Tips</div>
            <ul class="detail-list">
                <c:forEach var="pt" items="${disease.preventionArray}">
                    <li><i class="fas fa-star"></i> ${pt.trim()}</li>
                </c:forEach>
            </ul>
        </div>

        <div class="detail-card doctor-card">
            <div class="detail-card-header"><i class="fas fa-user-doctor"></i> Doctor Consultation Advice</div>
            <p class="doctor-advice-text">${disease.doctorAdvice}</p>
            <a href="${pageContext.request.contextPath}/appointment" class="btn-primary appointment-btn">
                <i class="fas fa-calendar-plus"></i> Book an Appointment
            </a>
        </div>

        <div class="detail-card emergency-detail-card full-width">
            <div class="detail-card-header"><i class="fas fa-triangle-exclamation"></i> Emergency Warning Signs</div>
            <p class="emergency-note">Seek <strong>immediate medical attention</strong> if you experience any of the following:</p>
            <ul class="detail-list emergency-list-detail">
                <c:forEach var="e" items="${disease.emergencyArray}">
                    <li><i class="fas fa-bolt"></i> ${e.trim()}</li>
                </c:forEach>
            </ul>
            <div class="emergency-numbers-small">
                <a href="tel:108" class="em-btn"><i class="fas fa-phone"></i> 108 Ambulance</a>
                <a href="tel:112" class="em-btn"><i class="fas fa-phone"></i> 112 Emergency</a>
            </div>
        </div>

    </div>

    <div class="back-nav">
        <a href="${pageContext.request.contextPath}/disease" class="btn-outline"><i class="fas fa-arrow-left"></i> Back to All Diseases</a>
        <a href="${pageContext.request.contextPath}/#chatbot" class="btn-primary"><i class="fas fa-robot"></i> Ask Chatbot</a>
    </div>
</section>

<jsp:include page="/WEB-INF/views/includes/footer.jsp"/>
