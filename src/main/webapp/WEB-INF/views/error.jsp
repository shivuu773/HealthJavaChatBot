<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/views/includes/header.jsp"/>
<section class="page-hero">
    <h1><i class="fas fa-exclamation-triangle"></i> Page Not Found</h1>
    <p>The page you're looking for doesn't exist.</p>
</section>
<section style="text-align:center;padding:4rem 2rem">
    <div style="font-size:5rem;margin-bottom:1rem">🏥</div>
    <h2 style="color:var(--text-primary);margin-bottom:1rem">Oops! 404 Error</h2>
    <p style="color:var(--text-secondary);margin-bottom:2rem">The page you requested could not be found.</p>
    <a href="${pageContext.request.contextPath}/home" class="btn-primary"><i class="fas fa-home"></i> Go to Home</a>
</section>
<jsp:include page="/WEB-INF/views/includes/footer.jsp"/>
