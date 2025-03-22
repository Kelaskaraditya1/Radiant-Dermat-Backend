package com.StarkIndustries.RadientDermat.authentication.model;

import com.StarkIndustries.RadientDermat.patients.model.Patients;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class UserPrinciples implements UserDetails {

    private Patients patients;

    public UserPrinciples(Patients patients){
        this.patients = patients;
    }

    public UserPrinciples(){

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("User"));
    }

    @Override
    public String getPassword() {
        return patients.getPassword();
    }

    @Override
    public String getUsername() {
        return patients.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
