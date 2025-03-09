package com.StarkIndustries.RadientDermat.authentication.service;

import com.StarkIndustries.RadientDermat.authentication.model.Patients;
import com.StarkIndustries.RadientDermat.authentication.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    @Autowired
    public PatientRepository patientRepository;

    @Autowired
    public BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public AuthenticationManager authenticationManager;

    @Autowired
    public JwtService jwtService;

    public boolean signup(Patients patients){
        if(!this.patientRepository.existsById(patients.getPatientId())){
            patients.setPassword(bCryptPasswordEncoder.encode(patients.getPassword()));
            this.patientRepository.save(patients);
            return true;
        }
        return false;
    }

    public String login(Patients patients){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(patients.getUsername(), patients.getPassword()));
        if(authentication.isAuthenticated()&&this.patientRepository.findByUsername(patients.getUsername())!=null)
            return this.jwtService.generateJwtToken(patients.getUsername());
        return "false";
    }

    public List<Patients> getPatients(){
        return patientRepository.findAll();
    }
}
