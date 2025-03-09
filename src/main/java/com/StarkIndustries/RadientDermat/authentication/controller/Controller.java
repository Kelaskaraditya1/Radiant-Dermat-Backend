package com.StarkIndustries.RadientDermat.authentication.controller;

import com.StarkIndustries.RadientDermat.authentication.model.Patients;
import com.StarkIndustries.RadientDermat.authentication.scheduler.Scheduler;
import com.StarkIndustries.RadientDermat.authentication.service.EmailService;
import com.StarkIndustries.RadientDermat.authentication.service.PatientService;
import com.StarkIndustries.RadientDermat.keys.Keys;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
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

    @GetMapping("/greetings")
    public ResponseEntity<String> greetings(){
        return ResponseEntity.status(HttpStatus.OK).body("Greetings\nI am Optimus Prime!!");
    }

    @PostMapping("/signup")
    public ResponseEntity<Patients> signup(@RequestParam("image")MultipartFile multipartFile, @RequestParam("user") String patients){
        try{
            Patients patients1 = this.objectMapper.readValue(patients, Patients.class);
            if(patientService.signup(patients1,multipartFile))
                return ResponseEntity.status(HttpStatus.OK).body(patients1);
        }catch (Exception e){
            e.printStackTrace();
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

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

}
