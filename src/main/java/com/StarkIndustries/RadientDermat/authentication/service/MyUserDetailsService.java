package com.StarkIndustries.RadientDermat.authentication.service;

import com.StarkIndustries.RadientDermat.authentication.model.Patients;
import com.StarkIndustries.RadientDermat.authentication.model.UserPrinciples;
import com.StarkIndustries.RadientDermat.authentication.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class MyUserDetailsService implements UserDetailsService {

//    @Autowired
//    public EntityManager entityManager;

    @Autowired
    public PatientRepository userRepo;

//    JpaRepositoryFactory jpaRepositoryFactory = new JpaRepositoryFactory(entityManager);
//    UserRepo userRepo = jpaRepositoryFactory.getRepository(UserRepo.class);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Patients patients = userRepo.findByUsername(username);
        return new UserPrinciples(patients);
    }
}
