package com.pradeep.backend.service;

import com.pradeep.backend.persistence.domain.backend.Plan;
import com.pradeep.backend.persistence.domain.backend.User;
import com.pradeep.backend.persistence.domain.backend.UserRole;
import com.pradeep.backend.persistence.repositories.PlanRepository;
import com.pradeep.backend.persistence.repositories.RoleRepository;
import com.pradeep.backend.persistence.repositories.UserRepository;
import com.pradeep.enums.PlansEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@Transactional(readOnly = true)
public class UserService {


    @Autowired
    private PlanRepository planRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Transactional
    public User createUser(User user, PlansEnum plansEnum, Set<UserRole> userRoles){

        String encryptPassword=passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptPassword);

        Plan plan=new Plan(plansEnum);
        //it make sure that plan exits in database
        if(!planRepository.existsById(plansEnum.getId())){
            plan=planRepository.save(plan);
        }

        user.setPlan(plan);
        for(UserRole ur :userRoles){
            roleRepository.save(ur.getRole());

        }
        user.getUserRoles().addAll(userRoles);
        user=userRepository.save(user);

        return user;


    }

}
