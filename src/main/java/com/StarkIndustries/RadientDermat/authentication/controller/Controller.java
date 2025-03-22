package com.StarkIndustries.RadientDermat.authentication.controller;

import com.StarkIndustries.RadientDermat.patients.model.Patients;
import com.StarkIndustries.RadientDermat.patients.model.UpdatePasswordModel;
import com.StarkIndustries.RadientDermat.patients.model.UpdatePatientModel;
import com.StarkIndustries.RadientDermat.authentication.scheduler.Scheduler;
import com.StarkIndustries.RadientDermat.authentication.service.EmailService;
import com.StarkIndustries.RadientDermat.patients.service.PatientService;
import com.StarkIndustries.RadientDermat.keys.Keys;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/auth")
public class Controller {

    @Autowired
    public PatientService patientService;

    @Autowired
    public ObjectMapper objectMapper;

    @Autowired
    public EmailService emailService;

    @Autowired
    public Scheduler scheduler;

    private static final long MIN_FILE_SIZE = 10 * 1024; // 10 KB

    private static final long MAX_FILE_SIZE = 10 * 1024 * 1024; // 10 MB

    @GetMapping("/greetings")
    public ResponseEntity<String> greetings(){
        return ResponseEntity.status(HttpStatus.OK).body("Greetings, I am Ironman!!");
    }

//    @PostMapping("/signup")
//    public ResponseEntity<Patients> signup(@RequestParam("image")MultipartFile multipartFile, @RequestParam("user") String patients){
//        try{
//            Patients patients1 = this.objectMapper.readValue(patients, Patients.class);
//            if(patientService.signup(patients1,multipartFile))
//                return ResponseEntity.status(HttpStatus.OK).body(patients1);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Patients patients) throws Exception{
        if(!patientService.login(patients).equals("false"))
            return ResponseEntity.status(HttpStatus.OK).body(patientService.login(patients));
        else
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @GetMapping("/get-patients")
    public ResponseEntity<List<Patients>> getPatients(){
        List<Patients> patientsList = patientService.getPatients();
        if(patientsList.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.status(HttpStatus.OK).body(patientsList);
    }

    @GetMapping("/send-email/{email}")
    public ResponseEntity<String> verifyEmail(@PathVariable("email") String email, HttpSession httpSession){
        Random random = new Random();
        int otp = 1000+random.nextInt(9000);
        httpSession.setAttribute(Keys.OTP,otp);
        if(this.emailService.verifyEmail(otp,email))
            return ResponseEntity.status(HttpStatus.OK).body("Email sent Successfully!!");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Enter valid email!!");
    }

    @GetMapping("/verify-email/{otp}")
    public ResponseEntity<String> verifyEmail(@PathVariable("otp") int otp,HttpSession session){
        if(Integer.parseInt(session.getAttribute(Keys.OTP).toString())==otp){
            session.removeAttribute(Keys.OTP);
            return ResponseEntity.status(HttpStatus.OK).body("Email verified Successfully!!");
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Enter valid otp!!");
    }

    @PutMapping("/update-password/{username}")
    public ResponseEntity<?> updatePassword(@RequestBody UpdatePasswordModel updatePasswordModel,@PathVariable("username") String username){

        Patients patients = patientService.updatePassword(updatePasswordModel,username);
        if(patients!=null)
            return ResponseEntity.status(HttpStatus.OK).body(patients);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    }

    @PostMapping("/signup")
    public ResponseEntity<Patients> registerPatient(@RequestBody Patients patients){
        Patients patients1 = this.patientService.signupPatient(patients);
        if(patients1!=null)
            return ResponseEntity.status(HttpStatus.CREATED).body(patients1);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @PutMapping("/add-profile-pic/{username}")
    public ResponseEntity<?> addProfilePic(@RequestParam("image") MultipartFile multipartFile, @PathVariable("username") String username){
        if(!multipartFile.isEmpty()){

            if(multipartFile.getSize()>=MIN_FILE_SIZE && multipartFile.getSize()<=MAX_FILE_SIZE){
                Patients patients = this.patientService.addProfilePic(username,multipartFile);
                if(patients!=null)
                    return ResponseEntity.status(HttpStatus.OK).body(patients);
            }else{
                if(multipartFile.getSize()>MAX_FILE_SIZE)
                    return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Image size should be less than 10 mb");
                else if(multipartFile.getSize()<MIN_FILE_SIZE)
                    return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Image size should be greater than 10 kb");
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("get-patient/{username}")
    public ResponseEntity<Patients> getPatientByUsername(@PathVariable("username") String username){

        Patients patients = this.patientService.getPatientByUsername(username);
        if(patients!=null)
            return ResponseEntity.status(HttpStatus.OK).body(patients);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/delete-patient/{patientId}")
    public ResponseEntity<?> deleteUser(@PathVariable("patientId") int patientId){
        if(this.patientService.deleteUser(patientId))
            return ResponseEntity.status(HttpStatus.OK).build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PutMapping("/update-profile/{username}")
    public ResponseEntity<Patients> updateProfile(@PathVariable("username") String username, @RequestBody UpdatePatientModel updatePatientModel){

        Patients patients = this.patientService.updatePatient(username,updatePatientModel);
        if(patients!=null)
            return ResponseEntity.status(HttpStatus.OK).body(patients);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PutMapping("/update-profile-pic/{username}")
    public ResponseEntity<?> updateProfilePic(@RequestParam("image") MultipartFile multipartFile,@PathVariable("username") String username){
        if(!multipartFile.isEmpty()){
            Patients patients = this.patientService.updatePatientProfilePic(username,multipartFile);
            if(patients!=null)
                return ResponseEntity.status(HttpStatus.OK).body(patients);
            else
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

//    @PutMapping("/add-medical-history/{username}")
//    public ResponseEntity<Patients> addMedicalHistory(@PathVariable("username") String username,@RequestBody PatientsMedicalHistory medicalHistory){
//
//            Patients patients = this.patientService.addMedicalHistory(username,medicalHistory);
//        if(patients!=null)
//            return ResponseEntity.status(HttpStatus.OK).body(patients);
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//
//    }

    @PutMapping("/forgot-password/{email}/{password}")
    public ResponseEntity<Patients> getPatientByEmail(@PathVariable("email") String email,@PathVariable("password") String password){
        var patients = this.patientService.findByEmail(email,password);
        if(patients!=null)
            return ResponseEntity.status(HttpStatus.OK).body(patients);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }



}
