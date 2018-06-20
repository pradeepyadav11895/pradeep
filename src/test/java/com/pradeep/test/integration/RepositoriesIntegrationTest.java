package com.pradeep.test.integration;


import com.pradeep.PradeepApplication;
import com.pradeep.backend.persistence.domain.backend.Plan;
import com.pradeep.backend.persistence.domain.backend.Role;
import com.pradeep.backend.persistence.domain.backend.User;
import com.pradeep.backend.persistence.domain.backend.UserRole;
import com.pradeep.backend.persistence.repositories.PlanRepository;
import com.pradeep.backend.persistence.repositories.RoleRepository;
import com.pradeep.backend.persistence.repositories.UserRepository;
import com.pradeep.enums.PlansEnum;
import com.pradeep.enums.RolesEnum;
import com.pradeep.utils.UsersUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.internal.Classes;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = PradeepApplication.class)
public class RepositoriesIntegrationTest {

    @Autowired
    private PlanRepository planRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;



    @Before
    public void init(){
        Assert.assertNotNull(planRepository);
        Assert.assertNotNull(roleRepository);
        Assert.assertNotNull(userRepository);
    }

    @Test
    public void testCreateNewPlan()throws Exception{
        Plan basicPlan=createPlan(PlansEnum.BASIC);
        planRepository.save(basicPlan);
        Optional<Plan> reterivedPlan=planRepository.findById(PlansEnum.BASIC.getId());
        Assert.assertNotNull(reterivedPlan);
        
    }

    @Test
    public void testCreateNewRole()throws Exception{
        Role userRole=createRole(RolesEnum.BASIC);
        roleRepository.save(userRole);
        Optional<Role> reterivedRole=roleRepository.findById(RolesEnum.BASIC.getId());
        Assert.assertNotNull(reterivedRole);

    }
    @Test
    public void testCreateNewUser() throws Exception{
        Plan basicPlan=createPlan(PlansEnum.BASIC);
        planRepository.save(basicPlan);

        User basicUser=UsersUtils.createBasicUSer();
        basicUser.setPlan(basicPlan);

        Role basicRole=createRole(RolesEnum.BASIC);
        Set<UserRole> userRoles=new HashSet<>();

        UserRole userRole=new UserRole(basicUser,basicRole);

        userRoles.add(userRole);

        basicUser.getUserRoles().addAll(userRoles);

        for(UserRole ur :userRoles){
            roleRepository.save(ur.getRole());
        }

        basicUser=userRepository.save(basicUser);
        Optional<User> newlyCreatedUser=userRepository.findById(basicUser.getId());
        Assert.assertNotNull(newlyCreatedUser);
        Assert.assertTrue(newlyCreatedUser.get().getId() !=0);
        Assert.assertNotNull(newlyCreatedUser.get().getPlan());
        Assert.assertNotNull(newlyCreatedUser.get().getPlan().getId());
        Set<UserRole> newlyCreateduserRoles=newlyCreatedUser.get().getUserRoles();
        for (UserRole ur:newlyCreateduserRoles){
            Assert.assertNotNull(ur.getRole());
            Assert.assertNotNull(ur.getRole().getId());
        }

    }


    private Plan createPlan(PlansEnum plansEnum) {
        return new Plan(plansEnum);
    }


    private Role createRole(RolesEnum rolesEnum) {
       return new Role(rolesEnum);
    }




}
