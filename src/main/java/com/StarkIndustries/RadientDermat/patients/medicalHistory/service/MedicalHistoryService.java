package com.StarkIndustries.RadientDermat.patients.medicalHistory.service;

import com.StarkIndustries.RadientDermat.patients.medicalHistory.model.MedicalHistory;
import com.StarkIndustries.RadientDermat.patients.medicalHistory.repository.MedicalHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicalHistoryService {

    @Autowired
    public MedicalHistoryRepository medicalHistoryRepository;

    public MedicalHistory addMedicalHistory(MedicalHistory medicalHistory){
        this.medicalHistoryRepository.save(medicalHistory);
        return medicalHistory;
    }

    public MedicalHistory updateMedicalHistory(MedicalHistory medicalHistory,int id){
        MedicalHistory medicalHistory1 = this.medicalHistoryRepository.findById(id).get();

        if(medicalHistory1!=null){

            medicalHistory1.setHeight(medicalHistory.getHeight());
            medicalHistory1.setWeight(medicalHistory.getWeight());

            if(!medicalHistory.getAllergicMedications().isEmpty()&&medicalHistory.getAllergicMedications()!=null)
                medicalHistory1.setAllergicMedications(medicalHistory.getAllergicMedications());

            if(!medicalHistory.getAllergies().isEmpty()&&medicalHistory.getAllergies()!=null)
                medicalHistory1.setAllergies(medicalHistory.getAllergies());

            if(!medicalHistory.getChronicIllness().isEmpty()&&medicalHistory.getChronicIllness()!=null)
                medicalHistory1.setChronicIllness(medicalHistory.getChronicIllness());

            medicalHistory1.setGender(medicalHistory.getGender());

            if(!medicalHistory.getInfections().isEmpty()&&medicalHistory.getInfections()!=null)
                medicalHistory1.setInfections(medicalHistory.getInfections());

            if(!medicalHistory.getPastSurgeries().isEmpty()&&medicalHistory.getPastSurgeries()!=null)
                medicalHistory1.setPastSurgeries(medicalHistory.getPastSurgeries());

            this.medicalHistoryRepository.save(medicalHistory1);
            return medicalHistory1;
        }
        return null;
    }

    public MedicalHistory getMedicalHistory(String username){
        MedicalHistory medicalHistory = this.medicalHistoryRepository.findByUsername(username);
        if(medicalHistory!=null)
            return medicalHistory;
        return null;
    }
}
