package com.StarkIndustries.RadientDermat.patients.repository;

import com.StarkIndustries.RadientDermat.patients.model.Patients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface PatientRepository extends JpaRepository<Patients,Integer> {

    public Patients findByUsername(String username);

    public Patients findByEmail(String email);

}
