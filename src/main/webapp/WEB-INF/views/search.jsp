<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/views/includes/header.jsp"/>

<section class="page-hero">
    <h1><i class="fas fa-search-plus"></i> Disease Search</h1>
    <p>Search diseases by name, symptoms, or description</p>
</section>

<section class="search-section">
    <div class="search-tabs">
        <button class="tab-btn active" onclick="switchTab('general')"><i class="fas fa-search"></i> Search by Name</button>
        <button class="tab-btn" onclick="switchTab('symptom')"><i class="fas fa-stethoscope"></i> Search by Symptom</button>
    </div>

    <div class="search-forms">
        <form id="generalForm" action="${pageContext.request.contextPath}/search" method="get" class="search-form active">
            <div class="search-input-group">
                <input type="text" name="q" value="${query}" placeholder="e.g., Diabetes, Malaria, Asthma..." class="big-search-input">
                <button type="submit" class="search-btn-large"><i class="fas fa-search"></i> Search</button>
            </div>
        </form>
        <form id="symptomForm" action="${pageContext.request.contextPath}/search" method="get" class="search-form" style="display:none">
            <div class="search-input-group">
                <input type="text" name="symptom" placeholder="e.g., fever, headache, cough, joint pain..." class="big-search-input">
                <button type="submit" class="search-btn-large"><i class="fas fa-stethoscope"></i> Find Diseases</button>
            </div>
            <div class="symptom-chips">
                <span class="chip" onclick="setSymptom('fever')">Fever</span>
                <span class="chip" onclick="setSymptom('cough')">Cough</span>
                <span class="chip" onclick="setSymptom('headache')">Headache</span>
                <span class="chip" onclick="setSymptom('fatigue')">Fatigue</span>
                <span class="chip" onclick="setSymptom('nausea')">Nausea</span>
                <span class="chip" onclick="setSymptom('joint pain')">Joint Pain</span>
                <span class="chip" onclick="setSymptom('breathing')">Breathing Issues</span>
                <span class="chip" onclick="setSymptom('rash')">Skin Rash</span>
                <span class="chip" onclick="setSymptom('dizziness')">Dizziness</span>
                <span class="chip" onclick="setSymptom('vomiting')">Vomiting</span>
            </div>
        </form>
    </div>

    <c:if test="${not empty query}">
    <div class="search-results-header">
        <c:choose>
            <c:when test="${searchType == 'symptom'}">
                <h2>Diseases matching symptom: <span class="highlight">"${query}"</span> — ${results.size()} found</h2>
            </c:when>
            <c:otherwise>
                <h2>Search results for: <span class="highlight">"${query}"</span> — ${results.size()} found</h2>
            </c:otherwise>
        </c:choose>
    </div>
    </c:if>

    <c:choose>
        <c:when test="${empty results && not empty query}">
            <div class="no-results">
                <i class="fas fa-search"></i>
                <h3>No results found for "${query}"</h3>
                <p>Try searching with different keywords or browse all diseases.</p>
                <a href="${pageContext.request.contextPath}/disease" class="btn-primary">Browse All Diseases</a>
            </div>
        </c:when>
        <c:otherwise>
            <div class="disease-grid">
                <c:forEach var="d" items="${results}">
                <div class="disease-card">
                    <div class="disease-card-header">
                        <div class="disease-card-icon"><i class="fas fa-stethoscope"></i></div>
                        <h3 class="disease-card-title">${d.name}</h3>
                    </div>
                    <p class="disease-desc">${d.description}</p>
                    <div class="disease-symptoms-preview">
                        <strong>Symptoms:</strong>
                        <c:forEach var="sym" items="${d.symptomsArray}" begin="0" end="2">
                            <span class="symptom-tag">${sym.trim()}</span>
                        </c:forEach>
                    </div>
                    <a href="${pageContext.request.contextPath}/disease?action=view&id=${d.id}" class="view-btn">
                        Full Details <i class="fas fa-arrow-right"></i>
                    </a>
                </div>
                </c:forEach>
            </div>
        </c:otherwise>
    </c:choose>
</section>

<jsp:include page="/WEB-INF/views/includes/footer.jsp"/>
<script>
function switchTab(type) {
    document.querySelectorAll('.tab-btn').forEach(b => b.classList.remove('active'));
    document.querySelectorAll('.search-form').forEach(f => f.style.display = 'none');
    if (type === 'general') {
        document.querySelectorAll('.tab-btn')[0].classList.add('active');
        document.getElementById('generalForm').style.display = 'flex';
    } else {
        document.querySelectorAll('.tab-btn')[1].classList.add('active');
        document.getElementById('symptomForm').style.display = 'block';
    }
}
function setSymptom(sym) {
    document.querySelector('#symptomForm input').value = sym;
}
</script>
