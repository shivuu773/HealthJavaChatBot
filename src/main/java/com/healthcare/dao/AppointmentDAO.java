package com.healthcare.dao;

import com.healthcare.db.DatabaseConnection;
import com.healthcare.model.Appointment;
import java.sql.*;
import java.util.*;

public class AppointmentDAO {

    public boolean bookAppointment(Appointment a) {
        String sql = "INSERT INTO appointments (patient_name,patient_email,patient_phone,doctor_name,department,appointment_date,appointment_time,symptoms,message) VALUES (?,?,?,?,?,?,?,?,?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, a.getPatientName());
            ps.setString(2, a.getPatientEmail());
            ps.setString(3, a.getPatientPhone());
            ps.setString(4, a.getDoctorName());
            ps.setString(5, a.getDepartment());
            ps.setString(6, a.getAppointmentDate());
            ps.setString(7, a.getAppointmentTime());
            ps.setString(8, a.getSymptoms());
            ps.setString(9, a.getMessage());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }

    public List<Appointment> getAllAppointments() {
        List<Appointment> list = new ArrayList<>();
        String sql = "SELECT * FROM appointments ORDER BY appointment_date DESC, appointment_time DESC";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) list.add(mapRow(rs));
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    public void saveChatLog(String sessionId, String userMsg, String botResponse) {
        String sql = "INSERT INTO chat_logs (session_id, user_message, bot_response) VALUES (?,?,?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, sessionId);
            ps.setString(2, userMsg);
            ps.setString(3, botResponse);
            ps.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    private Appointment mapRow(ResultSet rs) throws SQLException {
        Appointment a = new Appointment();
        a.setId(rs.getInt("id"));
        a.setPatientName(rs.getString("patient_name"));
        a.setPatientEmail(rs.getString("patient_email"));
        a.setPatientPhone(rs.getString("patient_phone"));
        a.setDoctorName(rs.getString("doctor_name"));
        a.setDepartment(rs.getString("department"));
        a.setAppointmentDate(rs.getString("appointment_date"));
        a.setAppointmentTime(rs.getString("appointment_time"));
        a.setSymptoms(rs.getString("symptoms"));
        a.setMessage(rs.getString("message"));
        a.setStatus(rs.getString("status"));
        a.setCreatedAt(rs.getString("created_at"));
        return a;
    }
}
