package com.healthcare.servlet;

import com.healthcare.dao.AppointmentDAO;
import com.healthcare.model.Appointment;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class AppointmentServlet extends HttpServlet {

    private AppointmentDAO appointmentDAO;

    @Override
    public void init() throws ServletException {
        appointmentDAO = new AppointmentDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/appointment.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        Appointment a = new Appointment();
        a.setPatientName(req.getParameter("patientName"));
        a.setPatientEmail(req.getParameter("patientEmail"));
        a.setPatientPhone(req.getParameter("patientPhone"));
        a.setDoctorName(req.getParameter("doctorName"));
        a.setDepartment(req.getParameter("department"));
        a.setAppointmentDate(req.getParameter("appointmentDate"));
        a.setAppointmentTime(req.getParameter("appointmentTime"));
        a.setSymptoms(req.getParameter("symptoms"));
        a.setMessage(req.getParameter("message"));

        boolean success = appointmentDAO.bookAppointment(a);

        if (success) {
            req.setAttribute("successMessage", "✅ Appointment booked successfully! We will confirm via email shortly.");
            req.setAttribute("appointmentData", a);
        } else {
            req.setAttribute("errorMessage", "❌ Failed to book appointment. Please try again.");
        }
        req.getRequestDispatcher("/WEB-INF/views/appointment.jsp").forward(req, resp);
    }
}
