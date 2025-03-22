package com.StarkIndustries.RadientDermat.authentication.service;

import com.StarkIndustries.RadientDermat.authentication.model.Patients;
import com.StarkIndustries.RadientDermat.authentication.model.PatientsMedicalHistory;
import com.StarkIndustries.RadientDermat.authentication.model.UpdatePasswordModel;
import com.StarkIndustries.RadientDermat.authentication.model.UpdatePatientModel;
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

    public Patients updatePassword(UpdatePasswordModel updatePasswordModel,String username){
        Patients patients = this.patientRepository.findByUsername(username);
        if(patients!=null){
            if(this.bCryptPasswordEncoder.matches(updatePasswordModel.getPassword(),patients.getPassword())){
                patients.setPassword(this.bCryptPasswordEncoder.encode(updatePasswordModel.getNewPassword()));
                this.patientRepository.save(patients);
                return patients;
            }
        }
        return null;
    }

    public Patients signupPatient(Patients patients){
        if(!this.patientRepository.existsById(patients.getPatientId())){
            patients.setPassword(this.bCryptPasswordEncoder.encode(patients.getPassword()));
            this.patientRepository.save(patients);
            return patients;
        }
        return null;
    }

    public Patients addProfilePic(String username,MultipartFile multipartFile){
        Patients patients = this.patientRepository.findByUsername(username);
        if(patients!=null){
            Map data = this.cloudinaryService.uploadDataToCloud(multipartFile);
            String profilePicUrl = data.get("secure_url").toString();
            patients.setProfilePicUrl(profilePicUrl);
            this.patientRepository.save(patients);
            return patients;
        }
        return null;
    }

    public Patients getPatientByUsername(String username){
        Patients patients = this.patientRepository.findByUsername(username);
        if(patients!=null)
            return patients;
        return null;
    }

    public boolean deleteUser(int patientId){
        if(this.patientRepository.existsById(patientId)){
            this.patientRepository.deleteById(patientId);
            return true;
        }
        return false;
    }

    public Patients updatePatientProfilePic(String username,MultipartFile multipartFile){
        Patients patients = this.patientRepository.findByUsername(username);
        if(patients!=null){
            Map data = this.cloudinaryService.uploadDataToCloud(multipartFile);
            String updatedProfilePicUrl = data.get("secure_url").toString();
            patients.setProfilePicUrl(updatedProfilePicUrl);
            this.patientRepository.save(patients);
            return patients;
        }return null;
    }

    public Patients updatePatient(String username, UpdatePatientModel updatePatientModel){

        Patients patients = this.patientRepository.findByUsername(username);

        if(patients!=null){
            patients.setName(updatePatientModel.getName());
            patients.setEmail(updatePatientModel.getEmail());
            this.patientRepository.save(patients);
            return patients;
        }
        return null;
    }

    public Patients addMedicalHistory(String username, PatientsMedicalHistory medicalHistory){
        Patients patients = this.patientRepository.findByUsername(username);
        if(patients!=null){
            patients.setMedicalHistory(medicalHistory.getMedicalHistory());
            this.patientRepository.save(patients);
            return patients;
        }
        return null;
    }

    public Patients findByEmail(String email,String password){
        Patients patients = this.patientRepository.findByEmail(email);
        if(patients!=null){
            patients.setPassword(this.bCryptPasswordEncoder.encode(password));
            this.patientRepository.save(patients);
            return patients;
        }
        return null;
    }


}
