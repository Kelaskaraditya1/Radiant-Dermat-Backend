package com.StarkIndustries.RadientDermat.patientTests.service;

import com.StarkIndustries.RadientDermat.authentication.model.Patients;
import com.StarkIndustries.RadientDermat.authentication.repository.PatientRepository;
import com.StarkIndustries.RadientDermat.cloudinary.service.CloudinaryService;
import com.StarkIndustries.RadientDermat.patientTests.models.PatientTestModel;
import com.StarkIndustries.RadientDermat.patientTests.repository.PatientsTestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Service
public class PatientsTestService {

    @Autowired
    public PatientsTestRepository patientsTestRepository;

    @Autowired
    public PatientRepository patientRepository;

    @Autowired
    public CloudinaryService cloudinaryService;

    public PatientTestModel addToHistory(PatientTestModel patientTestModel,String username){
        Patients patients = this.patientRepository.findByUsername(username);
        if(patients!=null){
            this.patientsTestRepository.save(patientTestModel);
            return patientTestModel;
        }
        return null;
    }

    public List<PatientTestModel> getTests(String username){
        Patients patients = this.patientRepository.findByUsername(username);
        if(patients!=null)
            return this.patientsTestRepository.findByUsername(username);
        return null;
    }

    public List<PatientTestModel> getAllTests(){
        List<PatientTestModel> patientTestModelList = this.patientsTestRepository.findAll();
        if(!patientTestModelList.isEmpty())
            return patientTestModelList;
        return null;
    }

    public boolean deleteTest(int testId){
        if(this.patientsTestRepository.existsById(testId)){
            this.patientsTestRepository.deleteById(testId);
            return true;
        }
        return false;
    }

    public PatientTestModel addTestImage(int testId, MultipartFile multipartFile){
        if(this.patientsTestRepository.existsById(testId)){
            PatientTestModel patientTestModel = this.patientsTestRepository.findById(testId).get();
            if(!multipartFile.isEmpty()){
                Map uploadData = this.cloudinaryService.uploadDataToCloud(multipartFile);
                System.out.println(uploadData.get("secure_url"));
                String uploadImageUrl = uploadData.get("secure_url").toString();
                patientTestModel.setDiseaseImageUrl(uploadImageUrl);
                this.patientsTestRepository.save(patientTestModel);
                return patientTestModel;
            }
        }
        return null;
    }
}
