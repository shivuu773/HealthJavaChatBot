<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<footer class="footer">
    <div class="footer-grid">
        <div class="footer-col">
            <h3><i class="fas fa-heartbeat"></i> HealthCare AI</h3>
            <p>AI-powered healthcare chatbot providing disease information, symptom guidance, and appointment booking services.</p>
            <div class="social-links">
                <a href="#" aria-label="Facebook"><i class="fab fa-facebook"></i></a>
                <a href="#" aria-label="Twitter"><i class="fab fa-twitter"></i></a>
                <a href="#" aria-label="Instagram"><i class="fab fa-instagram"></i></a>
                <a href="#" aria-label="LinkedIn"><i class="fab fa-linkedin"></i></a>
            </div>
        </div>
        <div class="footer-col">
            <h4>Quick Links</h4>
            <ul>
                <li><a href="${pageContext.request.contextPath}/home"><i class="fas fa-chevron-right"></i> Home</a></li>
                <li><a href="${pageContext.request.contextPath}/disease"><i class="fas fa-chevron-right"></i> Disease Info</a></li>
                <li><a href="${pageContext.request.contextPath}/search"><i class="fas fa-chevron-right"></i> Search Disease</a></li>
                <li><a href="${pageContext.request.contextPath}/appointment"><i class="fas fa-chevron-right"></i> Book Appointment</a></li>
            </ul>
        </div>
        <div class="footer-col">
            <h4>Emergency</h4>
            <ul class="emergency-list">
                <li><i class="fas fa-phone-alt"></i> Ambulance: <strong>108</strong></li>
                <li><i class="fas fa-phone-alt"></i> National Emergency: <strong>112</strong></li>
                <li><i class="fas fa-phone-alt"></i> Police: <strong>100</strong></li>
                <li><i class="fas fa-phone-alt"></i> Fire: <strong>101</strong></li>
                <li><i class="fas fa-phone-alt"></i> Women Helpline: <strong>1091</strong></li>
            </ul>
        </div>
        <div class="footer-col">
            <h4>Contact Hospital</h4>
            <p><i class="fas fa-map-marker-alt"></i> 123 Medical Center Road<br>Health City - 400001</p>
            <p><i class="fas fa-phone"></i> +91-22-1234-5678</p>
            <p><i class="fas fa-envelope"></i> info@healthcarehospital.com</p>
            <p><i class="fas fa-clock"></i> OPD: Mon-Sat, 8AM - 8PM</p>
        </div>
    </div>
    <div class="footer-bottom">
        <p>⚕️ <strong>Medical Disclaimer:</strong> This chatbot provides educational health information only. Always consult a qualified healthcare professional for medical advice, diagnosis, or treatment.</p>
        <p>&copy; 2025 HealthCare AI. Built with JSP, Servlets, JDBC & SQLite.</p>
    </div>
</footer>
</body>
</html>
