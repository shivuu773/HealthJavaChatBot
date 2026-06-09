package com.healthcare.db;

import java.sql.*;

public class PrintAppointments {
    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("        HEALTHCARE APPOINTMENTS DATABASE VIEWER     ");
        System.out.println("==================================================");
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {
             
            // Check if there are appointments
            String checkSql = "SELECT COUNT(*) FROM appointments";
            int count = 0;
            try (ResultSet rs = stmt.executeQuery(checkSql)) {
                if (rs.next()) {
                    count = rs.getInt(1);
                }
            }
            
            // If empty, insert a dummy appointment to show how it catches/saves data
            if (count == 0) {
                System.out.println("[INFO] No appointments found. Inserting a dummy appointment record...");
                String insertSql = "INSERT INTO appointments (patient_name, patient_email, patient_phone, doctor_name, department, appointment_date, appointment_time, symptoms, message) " +
                                  "VALUES ('Jane Doe', 'jane.doe@example.com', '+91-98765-43210', 'Dr. Smith', 'Cardiology', '2026-06-10', '10:30 AM', 'Chest discomfort and mild palpitation', 'Looking for a routine checkup.')";
                stmt.executeUpdate(insertSql);
                System.out.println("[INFO] Dummy appointment inserted successfully.");
            }
            
            // Query and print all records
            String selectSql = "SELECT * FROM appointments ORDER BY created_at DESC";
            try (ResultSet rs = stmt.executeQuery(selectSql)) {
                ResultSetMetaData meta = rs.getMetaData();
                int cols = meta.getColumnCount();
                
                System.out.println("\n--- APPOINTMENT RECORDS ---");
                while (rs.next()) {
                    System.out.println("ID:             " + rs.getInt("id"));
                    System.out.println("Patient Name:   " + rs.getString("patient_name"));
                    System.out.println("Email:          " + rs.getString("patient_email"));
                    System.out.println("Phone:          " + rs.getString("patient_phone"));
                    System.out.println("Doctor:         " + rs.getString("doctor_name"));
                    System.out.println("Department:     " + rs.getString("department"));
                    System.out.println("Date:           " + rs.getString("appointment_date"));
                    System.out.println("Time:           " + rs.getString("appointment_time"));
                    System.out.println("Symptoms:       " + rs.getString("symptoms"));
                    System.out.println("Message:        " + rs.getString("message"));
                    System.out.println("Status:         " + rs.getString("status"));
                    System.out.println("Created At:     " + rs.getString("created_at"));
                    System.out.println("--------------------------------------------------");
                }
            }
            
        } catch (SQLException e) {
            System.err.println("[ERROR] Database access error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
