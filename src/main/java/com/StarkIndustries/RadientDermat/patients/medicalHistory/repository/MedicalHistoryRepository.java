package com.StarkIndustries.RadientDermat.patients.medicalHistory.repository;

import com.StarkIndustries.RadientDermat.patients.medicalHistory.model.MedicalHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalHistoryRepository extends JpaRepository<MedicalHistory,Integer> {

    public MedicalHistory findByUsername(String  username);
}
