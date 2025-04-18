package com.StarkIndustries.RadientDermat.patients.medicalHistory.model;

import jakarta.persistence.*;

@Table
@Entity(name = "MedicalHistory")
public class MedicalHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int medicalHistoryId;

    @Column(name="height")
    private String height;

    @Column(name = "weight")
    private String weight;

    @Column(name = "gender")
    private String gender;

    @Column(name = "chronicIllness",columnDefinition = "TEXT")
    private String chronicIllness;

    @Column(name ="pastSurgeries",columnDefinition = "TEXT" )
    private String pastSurgeries;

    @Column(name = "infection",columnDefinition = "TEXT")
    private String infections;

    @Column(name = "allergies",columnDefinition = "TEXT")
    private String allergies;

    @Column(name = "allergicMedications",columnDefinition = "TEXT")
    private String allergicMedications;

    @Column(name = "username",columnDefinition = "TEXT")
    private String username;

    public MedicalHistory(int medicalHistoryId, String height, String weight, String gender, String chronicIllness, String pastSurgeries, String infections, String allergies, String allergicMedications, String username) {
        this.medicalHistoryId = medicalHistoryId;
        this.height = height;
        this.weight = weight;
        this.gender = gender;
        this.chronicIllness = chronicIllness;
        this.pastSurgeries = pastSurgeries;
        this.infections = infections;
        this.allergies = allergies;
        this.allergicMedications = allergicMedications;
        this.username = username;
    }

    public MedicalHistory(String height, String weight, String gender, String chronicIllness, String pastSurgeries, String infections, String allergies, String allergicMedications, String username) {
        this.height = height;
        this.weight = weight;
        this.gender = gender;
        this.chronicIllness = chronicIllness;
        this.pastSurgeries = pastSurgeries;
        this.infections = infections;
        this.allergies = allergies;
        this.allergicMedications = allergicMedications;
        this.username = username;
    }

    public MedicalHistory(String  height, String  weight, String  gender, String chronicIllness, String pastSurgeries, String infections, String allergies, String allergicMedications) {
        this.height = height;
        this.weight = weight;
        this.gender = gender;
        this.chronicIllness = chronicIllness;
        this.pastSurgeries = pastSurgeries;
        this.infections = infections;
        this.allergies = allergies;
        this.allergicMedications = allergicMedications;
    }

    public MedicalHistory(){

    }

    public int getMedicalHistoryId() {
        return medicalHistoryId;
    }

    public void setMedicalHistoryId(int medicalHistoryId) {
        this.medicalHistoryId = medicalHistoryId;
    }

    public String  getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String  getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String  getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getChronicIllness() {
        return chronicIllness;
    }

    public void setChronicIllness(String chronicIllness) {
        this.chronicIllness = chronicIllness;
    }

    public String getPastSurgeries() {
        return pastSurgeries;
    }

    public void setPastSurgeries(String pastSurgeries) {
        this.pastSurgeries = pastSurgeries;
    }

    public String getInfections() {
        return infections;
    }

    public void setInfections(String infections) {
        this.infections = infections;
    }

    public String getAllergies() {
        return allergies;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }

    public String getAllergicMedications() {
        return allergicMedications;
    }

    public void setAllergicMedications(String allergicMedications) {
        this.allergicMedications = allergicMedications;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "MedicalHistory{" +
                "medicalHistoryId=" + medicalHistoryId +
                ", height=" + height +
                ", weight=" + weight +
                ", gender=" + gender +
                ", chronicIllness='" + chronicIllness + '\'' +
                ", pastSurgeries='" + pastSurgeries + '\'' +
                ", infections='" + infections + '\'' +
                ", allergies='" + allergies + '\'' +
                ", allergicMedications='" + allergicMedications + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
