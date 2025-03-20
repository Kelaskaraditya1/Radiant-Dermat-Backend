package com.StarkIndustries.RadientDermat.authentication.model;

import jakarta.persistence.*;
import org.springframework.security.core.userdetails.User;

@Entity(name = "Patients")
public class Patients  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int patientId;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name="username")
    private String username;

    @Column(name="password")
    private String password;

    @Column(name = "profile_pic_url")
    private String profilePicUrl;

    @Column(name = "medicalHistory",columnDefinition = "TEXT")
    private String medicalHistory;

    public Patients(int patientId, String name, String email, String username, String password, String profilePicUrl, String medicalHistory) {
        this.patientId = patientId;
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
        this.profilePicUrl = profilePicUrl;
        this.medicalHistory = medicalHistory;
    }

    public Patients(int patientId, String name, String email, String username, String password, String profilePicUrl) {
        this.patientId = patientId;
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
        this.profilePicUrl = profilePicUrl;
    }

    public Patients(String name, String email, String username, String password, String profilePicUrl) {
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
        this.profilePicUrl = profilePicUrl;
    }

    public Patients(String name, String email, String username, String password) {
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public Patients(){

    }

    public int getPatientId() {
        return this.patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfilePicUrl() {
        return profilePicUrl;
    }

    public void setProfilePicUrl(String profilePicUrl) {
        this.profilePicUrl = profilePicUrl;
    }

    public String getMedicalHistory() {
        return medicalHistory;
    }

    public void setMedicalHistory(String medicalHistory) {
        this.medicalHistory = medicalHistory;
    }

    @Override
    public String toString() {
        return "Patients{" +
                "patientId=" + patientId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", profilePicUrl='" + profilePicUrl + '\'' +
                ", medicalHistory='" + medicalHistory + '\'' +
                '}';
    }
}
