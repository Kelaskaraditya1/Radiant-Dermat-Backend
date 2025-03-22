package com.StarkIndustries.RadientDermat.patients.patientTests.models;

import jakarta.persistence.*;

@Entity
@Table(name = "Patients_Tests")
public class PatientTestModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int testId;

    @Column(name="image_url")
    private String diseaseImageUrl;

    @Column(name = "name")
    private String diseaseName;

    @Column(name = "symptoms",columnDefinition = "TEXT")
    private String diseaseSymptoms;

    @Column(name="remedy",columnDefinition = "TEXT")
    private String diseaseRemedy;

    @Column(name = "username")
    private String username;

    public PatientTestModel(int testId, String diseaseImageUrl, String diseaseName, String diseaseSymptoms, String diseaseRemedy, String username) {
        this.testId = testId;
        this.diseaseImageUrl = diseaseImageUrl;
        this.diseaseName = diseaseName;
        this.diseaseSymptoms = diseaseSymptoms;
        this.diseaseRemedy = diseaseRemedy;
        this.username = username;
    }

    public PatientTestModel(int testId, String diseaseImageUrl, String diseaseName, String diseaseSymptoms, String diseaseRemedy) {
        this.testId = testId;
        this.diseaseImageUrl = diseaseImageUrl;
        this.diseaseName = diseaseName;
        this.diseaseSymptoms = diseaseSymptoms;
        this.diseaseRemedy = diseaseRemedy;
    }

    public PatientTestModel(String diseaseImageUrl, String diseaseName, String diseaseSymptoms, String diseaseRemedy) {
        this.diseaseImageUrl = diseaseImageUrl;
        this.diseaseName = diseaseName;
        this.diseaseSymptoms = diseaseSymptoms;
        this.diseaseRemedy = diseaseRemedy;
    }

    public PatientTestModel(String diseaseName, String diseaseSymptoms, String diseaseRemedy) {
        this.diseaseName = diseaseName;
        this.diseaseSymptoms = diseaseSymptoms;
        this.diseaseRemedy = diseaseRemedy;
    }

    public PatientTestModel() {
    }

    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    public String getDiseaseImageUrl() {
        return diseaseImageUrl;
    }

    public void setDiseaseImageUrl(String diseaseImageUrl) {
        this.diseaseImageUrl = diseaseImageUrl;
    }

    public String getDiseaseName() {
        return diseaseName;
    }

    public void setDiseaseName(String diseaseName) {
        this.diseaseName = diseaseName;
    }

    public String getDiseaseSymptoms() {
        return diseaseSymptoms;
    }

    public void setDiseaseSymptoms(String diseaseSymptoms) {
        this.diseaseSymptoms = diseaseSymptoms;
    }

    public String getDiseaseRemedy() {
        return diseaseRemedy;
    }

    public void setDiseaseRemedy(String diseaseRemedy) {
        this.diseaseRemedy = diseaseRemedy;
    }

    @Override
    public String toString() {
        return "PatientTestModel{" +
                "testId=" + testId +
                ", diseaseImageUrl='" + diseaseImageUrl + '\'' +
                ", diseaseName='" + diseaseName + '\'' +
                ", diseaseSymptoms='" + diseaseSymptoms + '\'' +
                ", diseaseRemedy='" + diseaseRemedy + '\'' +
                ", username='" + username + '\'' +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
