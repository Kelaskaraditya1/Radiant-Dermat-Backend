package com.StarkIndustries.RadientDermat.authentication.controller;

import com.StarkIndustries.RadientDermat.authentication.model.Patients;
import com.StarkIndustries.RadientDermat.authentication.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class Controller {

    @Autowired
    public PatientService patientService;

    @GetMapping("/greetings")
    public ResponseEntity<String> greetings(){
        return ResponseEntity.status(HttpStatus.OK).body("Greetings\nI am Optimus Prime!!");
    }

    @PostMapping("/signup")
    public ResponseEntity<Patients> signup(@RequestBody Patients patients){
        if(patientService.signup(patients))
            return ResponseEntity.status(HttpStatus.OK).body(patients);
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
}
