package com.StarkIndustries.RadientDermat.authentication.service;

import com.StarkIndustries.RadientDermat.authentication.model.Patients;
import com.StarkIndustries.RadientDermat.authentication.model.UpdatePasswordModel;
import com.StarkIndustries.RadientDermat.authentication.repository.PatientRepository;
import com.StarkIndustries.RadientDermat.cloudinary.service.CloudinaryService;
import com.StarkIndustries.RadientDermat.keys.Keys;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Service
public class PatientService {

    @Autowired
    public PatientRepository patientRepository;

    @Autowired
    public BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public AuthenticationManager authenticationManager;

    @Autowired
    public CloudinaryService cloudinaryService;

    @Autowired
    public JwtService jwtService;

    public boolean signup(Patients patients, MultipartFile multipartFile){

        Map uploadData = this.cloudinaryService.uploadDataToCloud(multipartFile);
        String profilePicUrl = uploadData.get("secure_url").toString();
        patients.setProfilePicUrl(profilePicUrl);
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

    public boolean verifyEmail(int otp, HttpSession httpSession){
         if(Integer.parseInt(httpSession.getAttribute(Keys.OTP).toString())==otp)
             return true;
         return false;
    }

    public boolean updatePassword(UpdatePasswordModel updatePasswordModel){
        Patients patients = this.patientRepository.findByUsername(updatePasswordModel.getUsername());
        if(patients!=null){
            if(this.bCryptPasswordEncoder.matches(updatePasswordModel.getPassword(),patients.getPassword())){
                patients.setPassword(this.bCryptPasswordEncoder.encode(updatePasswordModel.getNewPassword()));
                this.patientRepository.save(patients);
                return true;
            }
        }
        return false;
    }
}
