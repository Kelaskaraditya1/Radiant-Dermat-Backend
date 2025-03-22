package com.StarkIndustries.RadientDermat.patients.medicalHistory.controller;

import com.StarkIndustries.RadientDermat.patients.medicalHistory.model.MedicalHistory;
import com.StarkIndustries.RadientDermat.patients.medicalHistory.service.MedicalHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medical-history")
public class MedicalHistoryController {

    @Autowired
    public MedicalHistoryService medicalHistoryService;

    @PostMapping("/add-medical-history/{username}")
    public ResponseEntity<MedicalHistory> addMedicalHistory(@PathVariable("username") String username, @RequestBody MedicalHistory medicalHistory){
        MedicalHistory medicalHistory1 = this.medicalHistoryService.addMedicalHistory(medicalHistory);
        if(medicalHistory1!=null)
            return ResponseEntity.status(HttpStatus.OK).body(medicalHistory1);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @PutMapping("/update-medical-history/{id}")
    public ResponseEntity<MedicalHistory> updateMedicalHistory(@PathVariable("id") int id,@RequestBody MedicalHistory medicalHistory){
        MedicalHistory medicalHistory1 = this.medicalHistoryService.updateMedicalHistory(medicalHistory,id);
        if(medicalHistory1!=null)
            return ResponseEntity.status(HttpStatus.OK).body(medicalHistory1);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @GetMapping("/get-medical-history/{username}")
    public ResponseEntity<MedicalHistory> getMedicalHistory(@PathVariable("username") String username){
        MedicalHistory medicalHistory = this.medicalHistoryService.getMedicalHistory(username);
        if(medicalHistory!=null)
            return ResponseEntity.status(HttpStatus.OK).body(medicalHistory);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

}
