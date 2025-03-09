package com.StarkIndustries.RadientDermat.authentication.repository;

import com.StarkIndustries.RadientDermat.authentication.model.Patients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface PatientRepository extends JpaRepository<Patients,Integer> {

    public Patients findByUsername(String username);

}
