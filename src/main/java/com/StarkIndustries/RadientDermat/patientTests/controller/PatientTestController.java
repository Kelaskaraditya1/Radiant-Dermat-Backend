package com.StarkIndustries.RadientDermat.patientTests.controller;

import com.StarkIndustries.RadientDermat.patientTests.models.PatientTestModel;
import com.StarkIndustries.RadientDermat.patientTests.service.PatientsTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequestMapping("/test")
@RestController
public class PatientTestController {

    @Autowired
    public PatientsTestService patientsTestService;

    @GetMapping("/greetings")
    public ResponseEntity<String> greetings(){
        return ResponseEntity.status(HttpStatus.OK).body("Greetings,I am Optimus Prime!!");
    }

    @PostMapping("/add-to-history/{username}")
    public ResponseEntity<PatientTestModel> addToHistory(@RequestBody PatientTestModel patientTestModel,@PathVariable("username") String username){

        PatientTestModel patientTestModel1 = this.patientsTestService.addToHistory(patientTestModel,username);
        if(patientTestModel1!=null)
            return ResponseEntity.status(HttpStatus.OK).body(patientTestModel);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/get-tests/{username}")
    public  ResponseEntity<List<PatientTestModel>> getPatientTests(@PathVariable("username") String username){
        List<PatientTestModel> patientTestModelList = this.patientsTestService.getTests(username);
        if(!patientTestModelList.isEmpty())
            return ResponseEntity.status(HttpStatus.OK).body(patientTestModelList);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/get-all-tests")
    public ResponseEntity<List<PatientTestModel>> getAllTests(){
        List<PatientTestModel> patientTestModelList = this.patientsTestService.getAllTests();
        if(!patientTestModelList.isEmpty())
            return ResponseEntity.status(HttpStatus.OK).body(patientTestModelList);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/delete-test/{testId}")
    public ResponseEntity<?> deleteTest(@PathVariable("testId") int testId){
        if(this.patientsTestService.deleteTest(testId))
            return ResponseEntity.status(HttpStatus.OK).build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PutMapping("add-test-image/{testId}")
    public ResponseEntity<PatientTestModel> addTestImage(@PathVariable("testId") int testId,@RequestParam("image") MultipartFile multipartFile){
        PatientTestModel patientTestModel = this.patientsTestService.addTestImage(testId,multipartFile);
        if(patientTestModel!=null)
            return ResponseEntity.status(HttpStatus.OK).body(patientTestModel);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

}
