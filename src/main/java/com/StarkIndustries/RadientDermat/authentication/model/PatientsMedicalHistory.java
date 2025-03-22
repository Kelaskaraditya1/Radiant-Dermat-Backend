package com.StarkIndustries.RadientDermat.authentication.model;

public class PatientsMedicalHistory {

    private String medicalHistory;

    public PatientsMedicalHistory(String medicalHistory){
        this.medicalHistory=medicalHistory;
    }

    public PatientsMedicalHistory(){

    }

    public String getMedicalHistory() {
        return medicalHistory;
    }

    public void setMedicalHistory(String medicalHistory) {
        this.medicalHistory = medicalHistory;
    }

    @Override
    public String toString() {
        return "PatientsMedicalHistory{" +
                "medicalHistory='" + medicalHistory + '\'' +
                '}';
    }
}
