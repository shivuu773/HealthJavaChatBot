package com.healthcare.dao;

import com.healthcare.db.DatabaseConnection;
import com.healthcare.model.Disease;
import java.sql.*;
import java.util.*;

public class DiseaseDAO {

    public List<Disease> getAllDiseases() {
        List<Disease> list = new ArrayList<>();
        String sql = "SELECT * FROM diseases ORDER BY name";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) list.add(mapRow(rs));
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    public Disease getDiseaseById(int id) {
        String sql = "SELECT * FROM diseases WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapRow(rs);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public Disease getDiseaseByName(String name) {
        String sql = "SELECT * FROM diseases WHERE LOWER(name) = LOWER(?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapRow(rs);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public List<Disease> searchDiseases(String query) {
        List<Disease> list = new ArrayList<>();
        String sql = "SELECT * FROM diseases WHERE LOWER(name) LIKE LOWER(?) OR LOWER(symptoms) LIKE LOWER(?) OR LOWER(description) LIKE LOWER(?) ORDER BY name";
        String q = "%" + query + "%";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, q); ps.setString(2, q); ps.setString(3, q);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) list.add(mapRow(rs));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    public List<Disease> getDiseasesBySymptom(String symptom) {
        List<Disease> list = new ArrayList<>();
        String sql = "SELECT * FROM diseases WHERE LOWER(symptoms) LIKE LOWER(?) ORDER BY name";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + symptom + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) list.add(mapRow(rs));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    private Disease mapRow(ResultSet rs) throws SQLException {
        return new Disease(
            rs.getInt("id"),
            rs.getString("name"),
            rs.getString("description"),
            rs.getString("symptoms"),
            rs.getString("causes"),
            rs.getString("medicines"),
            rs.getString("precautions"),
            rs.getString("prevention_tips"),
            rs.getString("doctor_advice"),
            rs.getString("emergency_signs")
        );
    }
}
