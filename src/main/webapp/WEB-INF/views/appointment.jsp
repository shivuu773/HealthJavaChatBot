<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/views/includes/header.jsp"/>

<section class="page-hero">
    <h1><i class="fas fa-calendar-plus"></i> Book an Appointment</h1>
    <p>Schedule your visit with our specialized doctors</p>
</section>

<section class="appointment-section">

    <c:if test="${not empty successMessage}">
    <div class="alert alert-success">
        <i class="fas fa-check-circle"></i>
        <div>
            <strong>${successMessage}</strong>
            <p>Appointment Details: <strong>${appointmentData.patientName}</strong> — 
            ${appointmentData.appointmentDate} at ${appointmentData.appointmentTime} — 
            Dept: ${appointmentData.department}</p>
        </div>
    </div>
    </c:if>

    <c:if test="${not empty errorMessage}">
    <div class="alert alert-error">
        <i class="fas fa-exclamation-circle"></i>
        <strong>${errorMessage}</strong>
    </div>
    </c:if>

    <div class="appointment-layout">
        <div class="appointment-form-card">
            <h2><i class="fas fa-user-plus"></i> Patient Information</h2>
            <form action="${pageContext.request.contextPath}/appointment" method="post" class="appt-form" id="apptForm">

                <div class="form-row">
                    <div class="form-group">
                        <label for="patientName"><i class="fas fa-user"></i> Full Name *</label>
                        <input type="text" id="patientName" name="patientName" required placeholder="Enter your full name">
                    </div>
                    <div class="form-group">
                        <label for="patientPhone"><i class="fas fa-phone"></i> Phone Number *</label>
                        <input type="tel" id="patientPhone" name="patientPhone" required placeholder="+91 XXXXXXXXXX" pattern="[0-9+\-\s]{10,15}">
                    </div>
                </div>

                <div class="form-group">
                    <label for="patientEmail"><i class="fas fa-envelope"></i> Email Address *</label>
                    <input type="email" id="patientEmail" name="patientEmail" required placeholder="your@email.com">
                </div>

                <div class="form-row">
                    <div class="form-group">
                        <label for="department"><i class="fas fa-hospital"></i> Department *</label>
                        <select id="department" name="department" required onchange="updateDoctors()">
                            <option value="">-- Select Department --</option>
                            <option value="General Medicine">General Medicine</option>
                            <option value="Cardiology">Cardiology</option>
                            <option value="Neurology">Neurology</option>
                            <option value="Orthopedics">Orthopedics</option>
                            <option value="Dermatology">Dermatology</option>
                            <option value="Psychiatry">Psychiatry</option>
                            <option value="Pulmonology">Pulmonology</option>
                            <option value="Gastroenterology">Gastroenterology</option>
                            <option value="Endocrinology">Endocrinology</option>
                            <option value="Hematology">Hematology</option>
                            <option value="Rheumatology">Rheumatology</option>
                            <option value="Nephrology">Nephrology</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="doctorName"><i class="fas fa-user-doctor"></i> Preferred Doctor</label>
                        <select id="doctorName" name="doctorName">
                            <option value="Any Available Doctor">Any Available Doctor</option>
                        </select>
                    </div>
                </div>

                <div class="form-row">
                    <div class="form-group">
                        <label for="appointmentDate"><i class="fas fa-calendar"></i> Appointment Date *</label>
                        <input type="date" id="appointmentDate" name="appointmentDate" required>
                    </div>
                    <div class="form-group">
                        <label for="appointmentTime"><i class="fas fa-clock"></i> Preferred Time *</label>
                        <select id="appointmentTime" name="appointmentTime" required>
                            <option value="">-- Select Time --</option>
                            <option value="08:00 AM">08:00 AM</option>
                            <option value="09:00 AM">09:00 AM</option>
                            <option value="10:00 AM">10:00 AM</option>
                            <option value="11:00 AM">11:00 AM</option>
                            <option value="12:00 PM">12:00 PM</option>
                            <option value="02:00 PM">02:00 PM</option>
                            <option value="03:00 PM">03:00 PM</option>
                            <option value="04:00 PM">04:00 PM</option>
                            <option value="05:00 PM">05:00 PM</option>
                            <option value="06:00 PM">06:00 PM</option>
                        </select>
                    </div>
                </div>

                <div class="form-group">
                    <label for="symptoms"><i class="fas fa-notes-medical"></i> Current Symptoms</label>
                    <input type="text" id="symptoms" name="symptoms" placeholder="e.g., fever, headache, chest pain">
                </div>

                <div class="form-group">
                    <label for="message"><i class="fas fa-comment-medical"></i> Additional Notes</label>
                    <textarea id="message" name="message" rows="3" placeholder="Any additional information for the doctor..."></textarea>
                </div>

                <button type="submit" class="btn-primary submit-appt-btn">
                    <i class="fas fa-calendar-check"></i> Confirm Appointment
                </button>
            </form>
        </div>

        <div class="appointment-sidebar">
            <div class="sidebar-card">
                <h3><i class="fas fa-clock"></i> Working Hours</h3>
                <ul>
                    <li><span>OPD</span> <strong>Mon-Sat, 8 AM – 8 PM</strong></li>
                    <li><span>Emergency</span> <strong>24 × 7</strong></li>
                    <li><span>Lab</span> <strong>Mon-Sat, 7 AM – 9 PM</strong></li>
                    <li><span>Pharmacy</span> <strong>24 × 7</strong></li>
                </ul>
            </div>
            <div class="sidebar-card">
                <h3><i class="fas fa-map-marker-alt"></i> Location</h3>
                <p>123 Medical Center Road<br>Health City - 400001<br>Maharashtra, India</p>
                <p><i class="fas fa-phone"></i> +91-22-1234-5678</p>
                <p><i class="fas fa-envelope"></i> appointments@healthcarehospital.com</p>
            </div>
            <div class="sidebar-card emergency-sidebar">
                <h3><i class="fas fa-ambulance"></i> Emergency</h3>
                <a href="tel:108" class="em-btn-large"><i class="fas fa-phone"></i> Call 108 Now</a>
                <a href="tel:112" class="em-btn-large secondary"><i class="fas fa-phone"></i> Call 112</a>
            </div>
        </div>
    </div>
</section>

<jsp:include page="/WEB-INF/views/includes/footer.jsp"/>
<script>
// Set min date to today
document.getElementById('appointmentDate').min = new Date().toISOString().split('T')[0];

const doctorData = {
    'General Medicine': ['Dr. Rajesh Kumar','Dr. Priya Sharma','Dr. Anand Mehta'],
    'Cardiology': ['Dr. Suresh Nair','Dr. Kavitha Reddy'],
    'Neurology': ['Dr. Anil Gupta','Dr. Meera Pillai'],
    'Orthopedics': ['Dr. Rahul Singh','Dr. Deepa Joshi'],
    'Dermatology': ['Dr. Neha Patel','Dr. Vikram Rao'],
    'Psychiatry': ['Dr. Sanjay Mathur','Dr. Lakshmi Iyer'],
    'Pulmonology': ['Dr. Ramesh Verma','Dr. Sunita Shah'],
    'Gastroenterology': ['Dr. Kiran Desai','Dr. Ashok Kumar'],
    'Endocrinology': ['Dr. Pooja Agarwal','Dr. Vivek Sinha'],
    'Hematology': ['Dr. Mohan Das','Dr. Ritu Nair'],
    'Rheumatology': ['Dr. Satish Bhat','Dr. Amrita Rao'],
    'Nephrology': ['Dr. Girish Menon','Dr. Swati Patil']
};

function updateDoctors() {
    const dept = document.getElementById('department').value;
    const sel = document.getElementById('doctorName');
    sel.innerHTML = '<option value="Any Available Doctor">Any Available Doctor</option>';
    if (doctorData[dept]) {
        doctorData[dept].forEach(d => {
            const opt = document.createElement('option');
            opt.value = d; opt.textContent = d;
            sel.appendChild(opt);
        });
    }
}
</script>
