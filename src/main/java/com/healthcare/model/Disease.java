package com.healthcare.model;

public class Disease {
    private int id;
    private String name;
    private String description;
    private String symptoms;
    private String causes;
    private String medicines;
    private String precautions;
    private String preventionTips;
    private String doctorAdvice;
    private String emergencySigns;

    public Disease() {}

    public Disease(int id, String name, String description, String symptoms, String causes,
                   String medicines, String precautions, String preventionTips,
                   String doctorAdvice, String emergencySigns) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.symptoms = symptoms;
        this.causes = causes;
        this.medicines = medicines;
        this.precautions = precautions;
        this.preventionTips = preventionTips;
        this.doctorAdvice = doctorAdvice;
        this.emergencySigns = emergencySigns;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getSymptoms() { return symptoms; }
    public void setSymptoms(String symptoms) { this.symptoms = symptoms; }
    public String getCauses() { return causes; }
    public void setCauses(String causes) { this.causes = causes; }
    public String getMedicines() { return medicines; }
    public void setMedicines(String medicines) { this.medicines = medicines; }
    public String getPrecautions() { return precautions; }
    public void setPrecautions(String precautions) { this.precautions = precautions; }
    public String getPreventionTips() { return preventionTips; }
    public void setPreventionTips(String preventionTips) { this.preventionTips = preventionTips; }
    public String getDoctorAdvice() { return doctorAdvice; }
    public void setDoctorAdvice(String doctorAdvice) { this.doctorAdvice = doctorAdvice; }
    public String getEmergencySigns() { return emergencySigns; }
    public void setEmergencySigns(String emergencySigns) { this.emergencySigns = emergencySigns; }

    public String[] getSymptomsArray() {
        return symptoms != null ? symptoms.split("\\|") : new String[0];
    }
    public String[] getMedicinesArray() {
        return medicines != null ? medicines.split("\\|") : new String[0];
    }
    public String[] getPrecautionsArray() {
        return precautions != null ? precautions.split("\\|") : new String[0];
    }
    public String[] getPreventionArray() {
        return preventionTips != null ? preventionTips.split("\\|") : new String[0];
    }
    public String[] getEmergencyArray() {
        return emergencySigns != null ? emergencySigns.split("\\|") : new String[0];
    }
    public String[] getCausesArray() {
        return causes != null ? causes.split("\\|") : new String[0];
    }
}
