package com.StarkIndustries.RadientDermat.patients.model;

public class UpdatePasswordModel {

    private String password;

    private String newPassword;


    public UpdatePasswordModel(String password, String newPassword) {
        this.password = password;
        this.newPassword = newPassword;
    }

    public UpdatePasswordModel(){

    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    @Override
    public String toString() {
        return "UpdatePasswordModel{" +
                ", password='" + password + '\'' +
                ", newPassword='" + newPassword + '\'' +
                '}';
    }
}
