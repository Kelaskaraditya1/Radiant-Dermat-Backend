package com.StarkIndustries.RadientDermat.patientTests.repository;

import com.StarkIndustries.RadientDermat.authentication.model.Patients;
import com.StarkIndustries.RadientDermat.patientTests.models.PatientTestModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatientsTestRepository extends JpaRepository<PatientTestModel,Integer> {

    public List<PatientTestModel> findByUsername(String username);

}
