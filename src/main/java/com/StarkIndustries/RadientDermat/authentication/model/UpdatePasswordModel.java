package com.StarkIndustries.RadientDermat.authentication.model;

public class UpdatePasswordModel {

    private String username;

    private String password;

    private String newPassword;

    public UpdatePasswordModel(String username, String password, String newPassword) {
        this.username = username;
        this.password = password;
        this.newPassword = newPassword;
    }

    public UpdatePasswordModel(String password, String newPassword) {
        this.password = password;
        this.newPassword = newPassword;
    }

    public UpdatePasswordModel(){

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

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    @Override
    public String toString() {
        return "UpdatePasswordModel{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", newPassword='" + newPassword + '\'' +
                '}';
    }
}
