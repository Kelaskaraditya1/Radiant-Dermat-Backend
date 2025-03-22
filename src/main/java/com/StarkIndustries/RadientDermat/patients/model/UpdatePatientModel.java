package com.StarkIndustries.RadientDermat.patients.model;

public class UpdatePatientModel {


    private String name;

    private String email;



    public UpdatePatientModel(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public UpdatePatientModel(){

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



    @Override
    public String toString() {
        return "UpdatePatientModel{" +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
